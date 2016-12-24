/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.validation.Valid;
import model.Empresa;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.*;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class EmpresaController
{

    private EmpresaRepository db = new EmpresaRepository();

    @RequestMapping(value = "emp-save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public @ResponseBody
    String save(@Valid Empresa empresa, BindingResult result)
    {
        if (result.hasErrors())
            return new OperationResult(StatusRetorno.FALHA_VALIDACAO, result.getFieldErrors().get(0).getDefaultMessage(), "").toJson();
        
        db.enableAutoCommitAndClose();
        
        if (db.exists(Empresa.class, "id", empresa.getId()))
            db.update(empresa);
        else
            db.save(empresa);

        return (empresa.saved || empresa.updated
                ? new OperationResult(StatusRetorno.OPERACAO_OK, "Empresa salva.", "").toJson()
                : new OperationResult(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar a empresa.", "").toJson());
    }

    @RequestMapping(value = "emp-find", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        db.enableAutoCommitAndClose();
        Empresa emp = db.find(Empresa.class, id);

        return (emp.getId() > 0
                ? new OperationResult(StatusRetorno.OPERACAO_OK, "", emp).toJson()
                : new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado.", "").toJson());
    }

    @RequestMapping(value = "emp-search", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String search(@RequestParam(value = "query") String searchTerm)
    {
        List<Empresa> list = (searchTerm.isEmpty()
                ? db.listAll()
                : db.search(searchTerm));

        return (list.isEmpty()
                ? new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Nenhum registro encontrado.", "").toJson()
                : new OperationResult(StatusRetorno.OPERACAO_OK, list.size() + " registros encontrados", "").toJson());
    }
}
