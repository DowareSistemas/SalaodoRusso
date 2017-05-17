/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.Utility;
import dao.ConfiguracoesDao;
import dao.ProdutoDao;
import enums.StatusRetorno;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import model.Configuracoes;
import model.Produto;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
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
public class ProdutoController
{

    @RequestMapping(value = "produto-save", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String save(@Valid Produto produto, BindingResult result)
    {
        if (result.hasErrors())
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, Utility.getBindingResultMessage(result), "");

        ProdutoDao dao = new ProdutoDao(false);
        if (produto.getId() > 0)
            produto.setFoto(dao.find(produto.getId()).getFoto());
        else
            produto.setFoto("");
        dao.save(produto);
        dao.commit(true);

        return (produto.saved || produto.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Produto salvo com sucesso", produto.getId())
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar o produto", ""));
    }

    @RequestMapping(value = "produto-remove", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        ProdutoDao dao = new ProdutoDao(false);
        Produto produto = dao.find(id);
        dao.remove(produto);
        dao.commit(true);

        if (produto.deleted)
            Utility.removeImageInDisk(produto.getFoto());

        return (produto.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Produto removido", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao remover o produto", ""));
    }

    @RequestMapping(value = "produto-find", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        ProdutoDao dao = new ProdutoDao(true);
        Produto produto = dao.find(id);

        return (produto.getId() == 0
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", produto));
    }

    @RequestMapping(value = "produto-search", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String search(
            @RequestParam(value = "searchTerm") String searchTerm)
    {
        ProdutoDao dao = new ProdutoDao(true);
        List<Produto> list = dao.search(searchTerm);

        return OperationResult.toJson(StatusRetorno.OPERACAO_OK, list.size() + " registros encontrados", list);
    }

    @RequestMapping(value = "/produto-setimage", produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String setImage(int produto_id, MultipartFile file)
    {
        String nomeImagem = Utility.saveImageInDisk(file);
        if (nomeImagem == null)
            return OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar a imagem do produto", "");

        ProdutoDao dao = new ProdutoDao();
        Produto produto = dao.find(produto_id);
        Utility.removeImageInDisk(produto.getFoto());
        produto.setFoto(nomeImagem);
        dao.save(produto);
        dao.commit(true);

        return (produto.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao alterar a imagem do produto", ""));
    }

    @RequestMapping(value = "/produto-getimage")
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
    
    @RequestMapping(value = "/produto-top8", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String top8()
    {
        ProdutoDao dao = new ProdutoDao(true);
        List<Produto> list = dao.top8();
        
        return OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", list);
    }
}
