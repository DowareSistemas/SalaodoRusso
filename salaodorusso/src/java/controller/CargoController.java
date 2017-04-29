/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.Utility;
import dao.CargoDao;
import enums.StatusRetorno;
import java.util.List;
import javax.validation.Valid;
import model.Cargo;
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
public class CargoController
{
    @RequestMapping(value = "/cargo-save", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String save(@Valid Cargo cargo, BindingResult result)
    {
        if(result.hasErrors())
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, Utility.getBindingResultMessage(result), "");
      
        CargoDao dao = new CargoDao(true);
        dao.save(cargo);
        
        return (cargo.saved || cargo.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Cargo salvo com sucesso", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar o cargo", ""));
    }
    
    @RequestMapping(value = "/cargo-find", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        CargoDao dao = new CargoDao(true);
        Cargo cargo = dao.find(id);
        
        return (cargo.getId() == 0
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", cargo));
    }
    
    @RequestMapping(value = "/cargo-remove", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        CargoDao dao = new CargoDao(false);
        Cargo cargo = dao.find(id);
        
        if(cargo.getId() == 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "");
        }
        
        if(cargo.getFuncionario().toList().size() > 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, "Não é possível remover este cargo. Existem um ou mais funcionarios relacionados a ele.", "");
        }
        
        dao.remove(cargo);
        dao.commit(true);
        
        return (cargo.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Cargo removido", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao remover o cargo", ""));
    }
    
    @RequestMapping(value = "/cargo-search", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String search(@RequestParam(value = "search") String search)
    {
        CargoDao dao = new CargoDao(true);
        List<Cargo> cargos = dao.search(search);
        
        return OperationResult.toJson(StatusRetorno.OPERACAO_OK, cargos.size() + " registros encontrados.", cargos);
    }
}
