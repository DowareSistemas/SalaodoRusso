/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.Utility;
import dao.EnderecoDao;
import enums.StatusRetorno;
import javax.validation.Valid;
import model.Endereco;
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
public class EnderecoController
{
    @RequestMapping(value = "/endereco-save", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String save(@Valid Endereco endereco, BindingResult result)
    {
        if(result.hasErrors())
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, Utility.getBindingResultMessage(result), "");
        
        EnderecoDao dao = new EnderecoDao(true);
        dao.save(endereco);
        
        return (endereco.saved || endereco.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Endereço salvo com sucesso", endereco.getId())
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Tivemos uma falha interna ao salvar seu endereço. Iremos resolver este problema o mais rápido possível. Nos desculpe por este transtorno :(", ""));
    }
    
    @RequestMapping(value = "/endereco-find", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        EnderecoDao dao = new EnderecoDao(true);
        Endereco endereco = dao.find(id);
        
        return (endereco.getId() == 0
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", endereco));
    }
    
    @RequestMapping(value = "/endereco-remove", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        EnderecoDao dao = new EnderecoDao();
        Endereco endereco = dao.find(id);
        
        if(endereco.getId() == 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "");
        }
        
        dao.remove(endereco);
        dao.commit(true);
        
        return (endereco.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Endereço removido com sucesso", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Tivemos uma falha interna ao remover o endereço. Iremos resolver este problema o mais rápido possível. Nos desculpe por este transtorno :(", ""));
    }
}
