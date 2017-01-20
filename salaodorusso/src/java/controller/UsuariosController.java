/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.Usuarios;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.UsuariosRepository;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class UsuariosController
{
    private UsuariosRepository db = new UsuariosRepository();

    @RequestMapping(value = "/usr-find", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        db.enableAutoCommitAndClose();
        Usuarios usr = db.find(Usuarios.class, id);

        return (usr.getId() == 0
                ? new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado.", "").toJson()
                : new OperationResult(StatusRetorno.OPERACAO_OK, "", usr).toJson());
    }

    @RequestMapping(value = "/usr-login", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String login(@RequestParam(value = "nome") String nome, @RequestParam(value = "senha") String senha, HttpSession httpSession)
    {
        Usuarios usuario = db.efetuaLogin(nome, senha);
        if (usuario != null)
        {
            if (usuario.getId() > 0)
            {
                httpSession.setAttribute("usuario", nome);
                return new OperationResult(StatusRetorno.OPERACAO_OK, "Usuário autenticado.", "").toJson();
            }
        }

        return new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Usuário ou senha incorretos.", "").toJson();
    }

    @RequestMapping(value = "/usr-search", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String search(@RequestParam(value = "query") String searchTerm, @RequestParam(value = "tipo_usuario") int tipo)
    {
        List<Usuarios> list = (searchTerm.isEmpty()
                ? db.listAll(tipo)
                : db.search(searchTerm, tipo));

        return (list.isEmpty()
                ? new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Nenhum registro encontrado.", "").toJson()
                : new OperationResult(StatusRetorno.OPERACAO_OK, list.size() + " registros encotrados.", list).toJson());
    }

    @RequestMapping(value = "/usr-save", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String save(@Valid Usuarios usuario, BindingResult result, HttpSession httpSession)
    {
        if (result.hasErrors())
            return new OperationResult(StatusRetorno.FALHA_VALIDACAO, result.getFieldErrors().get(0).getDefaultMessage(), "").toJson();

        if (db.exists(Usuarios.class, "id", usuario.getId()))
            db.update(usuario);
        else
            db.save(usuario);

        db.commit(true);

        return (usuario.saved || usuario.updated
                ? new OperationResult(StatusRetorno.OPERACAO_OK, "Usuário salvo", "").toJson()
                : new OperationResult(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar o usuário.", "").toJson());
    }
}
