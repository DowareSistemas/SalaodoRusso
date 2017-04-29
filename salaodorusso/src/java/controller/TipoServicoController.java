/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.Utility;
import dao.TipoServicoDao;
import enums.StatusRetorno;
import java.util.List;
import javax.validation.Valid;
import model.TipoServico;
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
public class TipoServicoController
{
    @RequestMapping(value = "/tiposervico-save", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String save(@Valid TipoServico tipoServico, BindingResult result)
    {
        if(result.hasErrors())
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, Utility.getBindingResultMessage(result), "");
        
        TipoServicoDao dao = new TipoServicoDao(true);
        dao.save(tipoServico);
        
        return (tipoServico.saved || tipoServico.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Tipo de serviço salvo", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar o tipo de serviço", ""));
    }
    
    @RequestMapping(value = "/tiposervico-find", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        TipoServicoDao dao = new TipoServicoDao(true);
        TipoServico tipoServico = dao.find(id);
        
        return (tipoServico.getId() == 0
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", tipoServico));
    }
    
    @RequestMapping(value = "/tiposervico-remove", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        TipoServicoDao dao  = new TipoServicoDao(true);
        TipoServico tipoServico = dao.find(id);
        
        if(tipoServico.getId() == 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "");
        }
        
        if(tipoServico.getFuncionarioServicos().toList().size() > 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, "Não é possível remover este tipo de serviço. Ele está relacionado a um ou mais funcionários", "");
        }
        
        dao.remove(tipoServico);
        dao.commit(true);
        
        return (tipoServico.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Tipo de serviço removido", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao remover o tipo de serviço", ""));
    }
    
    @RequestMapping(value = "/tiposervico-search", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String search(@RequestParam(value = "search") String search)
    {
        TipoServicoDao dao = new TipoServicoDao(true);
        List<TipoServico> list = dao.search(search);
        
        return OperationResult.toJson(StatusRetorno.OPERACAO_OK, list.size() + " registros encontrados", list);
    }
}
