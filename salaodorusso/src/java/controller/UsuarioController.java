/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.Utility;
import dao.UsuarioDao;
import enums.StatusRetorno;
import javax.validation.Valid;
import model.DataEntry;
import model.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class UsuarioController
{

    @RequestMapping(value = "/usuario-save", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String save(@Valid Usuario usuario, BindingResult result)
    {
        if (result.hasErrors())
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, Utility.getBindingResultMessage(result), "");

        UsuarioDao dao = new UsuarioDao(true);
        dao.save(usuario);

        String dataKey = null;

        if (usuario.saved)
            dataKey = generateDataKey(usuario);

        return (usuario.saved || usuario.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Conta de usuário salva com sucesso", dataKey)
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Tivemos uma falha interna ao salvar sua conta de usuário. Iremos resolver este problema o mais rápido possível. Nos desculpe por este transtorno :(", ""));
    }

    private String generateDataKey(Usuario usuario)
    {
        Database db = new Database();
        DataEntry de = new DataEntry();
        de.setDataKey(db.generateDataKey());
        de.setDataValue(usuario);
        db.saveChanges(de);

        return de.getDataKey();
    }

    @RequestMapping(value = "/usuario-remove", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        UsuarioDao dao = new UsuarioDao(false);
        Usuario usuario = dao.find(id);

        if (usuario.getId() == 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Esta conta de usuário não existe", "");
        }

        dao.remove(usuario);
        dao.commit(true);

        return (usuario.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Conta de usuário removida com sucesso", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Tivemos uma falha interna ao remover sua conta de usuário. Iremos resolver este problema o mais rápido possível. Nos nos desculpe por este transtorno :(", ""));
    }

    @RequestMapping(value = "/usuario-findbyid", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        UsuarioDao dao = new UsuarioDao(true);
        Usuario usuario = dao.find(id);

        return (usuario.getId() == 0
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Esta conta de usuário não existe", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", usuario));
    }

    @RequestMapping(value = "/usuario-findbykey", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "dataKey") String dataKey)
    {
        Usuario usuarioCache = findByKey(dataKey);
        if (usuarioCache == null)
            return OperationResult.toJson(StatusRetorno.NAO_AUTORIZADO,
                    "Nosso serviço não pode atender a sua solicitação por que seu login foi expirado. Efetue login novamente.", "");

        Usuario usuarioDB = new UsuarioDao(true).find(usuarioCache.getId());
        return OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", usuarioDB);
    }

    private Usuario findByKey(String dataKey)
    {
        try
        {
            Database db = new Database();
            DataEntry entry = db.read(dataKey);
            if (entry == null)
                return null;
            Usuario usuario = (Usuario) entry.getDataValue();
            return usuario;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    @RequestMapping(value = "/usuario-efetualogin", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String efetuaLogin(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "senha") String senha)
    {
        UsuarioDao dao = new UsuarioDao(true);
        Usuario usuario = dao.efetuaLogin(email, senha);

        return (usuario == null
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Usuário ou senha incorretos", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", generateDataKey(usuario)));
    }

    @RequestMapping(value = "/usuario-logout", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String logout(@RequestParam(value = "dataKey") String dataKey)
    {
        try
        {
            Database db = new Database();
            db.drop(dataKey);

            return OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Usuário desconectado com exito.", "");
        }
        catch (Exception ex)
        {
            return OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao desconectar o usuário", "");
        }
    }

}
