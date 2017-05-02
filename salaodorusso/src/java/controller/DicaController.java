/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.Utility;
import dao.ConfiguracoesDao;
import dao.DicaDao;
import enums.StatusRetorno;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import model.Configuracoes;
import model.Dica;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class DicaController
{

    @RequestMapping(value = "/dica-save", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String save(@Valid Dica dica, BindingResult result)
    {
        if (result.hasErrors())
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, Utility.getBindingResultMessage(result), "");

        DicaDao dao = new DicaDao(false);
        if (dica.getId() > 0)
            dica.setFoto(dao.find(dica.getId()).getFoto());
        else
            dica.setFoto("");
        dao.save(dica);
        dao.commit(true);

        return (dica.saved || dica.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Dica salva com sucesso", dica.getId())
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar a dica", ""));
    }

    @RequestMapping(value = "/dica-find", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        DicaDao dao = new DicaDao(true);
        Dica dica = dao.find(id);

        return (dica.getId() == 0
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", dica));
    }

    @RequestMapping(value = "/dica-remove", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        DicaDao dao = new DicaDao(false);
        Dica dica = dao.find(id);

        if (dica.getId() == 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "");
        }

        dao.remove(dica);
        dao.commit(true);

        if (dica.deleted)
            Utility.removeImageInDisk(dica.getFoto());

        return (dica.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Dica removida", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar a dica", ""));
    }

    @RequestMapping(value = "/dica-search", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String search(
            @RequestParam(value = "searchTerm") String search,
            @RequestParam(value = "paginaAtual") int paginaAtual,
            @RequestParam(value = "numeroRegistros") int numeroRegistros)
    {
        DicaDao dao = new DicaDao(true);
        List<Dica> dicas = dao.search(search, paginaAtual, numeroRegistros);

        return OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", dicas);
    }

    @RequestMapping(value = "/dica-setimage", produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String setImage(int dica_id, MultipartFile file)
    {
        String nomeImagem = Utility.saveImageInDisk(file);
        if (nomeImagem == null)
            return OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar a imagem da dica", "");

        DicaDao dao = new DicaDao();
        Dica dica = dao.find(dica_id);
        Utility.removeImageInDisk(dica.getFoto());
        dica.setFoto(nomeImagem);
        dao.save(dica);
        dao.commit(true);

        return (dica.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao atualizar a imagem da dica", ""));
    }

    @RequestMapping(value = "/dica-getimage",  method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@RequestParam(value = "image_name") String nomeFoto, HttpServletResponse response)
    {
        try
        {
            Configuracoes config = ConfiguracoesDao.getInstance().find("FOTO_PATH");
            InputStream inputStream = (new File(config.getValor() + nomeFoto).exists()
                    ? new FileInputStream(config.getValor() + nomeFoto)
                    : new FileInputStream(config.getValor() + "default.png"));
            byte[] result = IOUtils.toByteArray(inputStream);

            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(result);
            responseOutputStream.flush();
            responseOutputStream.close();
        }
        catch (Exception ex)
        {
            LogController.write("MudasController", "getImage", "foto: " + nomeFoto + " \n " + ex.getMessage());
        }
    }
}
