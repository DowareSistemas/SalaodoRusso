/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.Utility;
import dao.ExpedienteDao;
import enums.StatusRetorno;
import java.util.List;
import javax.validation.Valid;
import model.Expediente;
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
public class ExpedienteController
{

    @RequestMapping(value = "/expediente-save", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String save(@Valid Expediente expediente, BindingResult result)
    {
        if (result.hasErrors())
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, Utility.getBindingResultMessage(result), "");

        ExpedienteDao dao = new ExpedienteDao(true);
        dao.save(expediente);

        return (expediente.saved || expediente.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Expediente salvo com sucesso", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar o expediente.", ""));
    }

    @RequestMapping(value = "/expediente-find", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        ExpedienteDao dao = new ExpedienteDao(true);
        Expediente expediente = dao.find(id);

        return (expediente.getId() == 0
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", expediente));
    }

    @RequestMapping(value = "/expediente-remove", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        ExpedienteDao dao = new ExpedienteDao(false);
        Expediente expediente = dao.find(id);

        if (expediente.getFuncionario().toList().size() > 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, "Não é possível excluir este expediente. Existem um ou mais funcionarios relacionados a ele.", "");
        }

        if (expediente.getId() == 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "");
        }

        dao.remove(expediente);
        dao.commit(true);

        return (expediente.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Expediente removido", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao remover o expediente", ""));
    }
    
    @RequestMapping(value = "/expediente-listall", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String listAll()
    {
        ExpedienteDao dao = new ExpedienteDao(true);
        List<Expediente> list  =dao.listAll();
        
        return OperationResult.toJson(StatusRetorno.OPERACAO_OK, list.size() + " registros encontrados", list);
    }
}
