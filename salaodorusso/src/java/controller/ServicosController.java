/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.validation.Valid;
import model.Servicos;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.ServicosRepository;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class ServicosController
{
    ServicosRepository db = new ServicosRepository();
    
    @RequestMapping(value = "/serv-save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public @ResponseBody
    String save(@Valid Servicos servico, BindingResult result)
    {
        if(result.hasErrors())
            return new OperationResult(StatusRetorno.FALHA_VALIDACAO, result.getFieldErrors().get(0).getDefaultMessage(), "").toJson();
        
        if(db.exists(Servicos.class, "id", servico.getId()))
            db.update(servico);
        else
            db.save(servico);
        db.commit(true);
        
        return (servico.saved || servico.updated
                ? new OperationResult(StatusRetorno.OPERACAO_OK, "Serviço salvo.", "").toJson()
                : new OperationResult(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar o serviço.", "").toJson());
    }
    
    @RequestMapping(value = "/serv-search", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String search(@RequestParam(value = "query") String searchTerm)
    {
        List<Servicos> result = 
                (searchTerm.isEmpty()
                ? db.listAll()
                : db.search(searchTerm));
        
        return (result.isEmpty()
                ? new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Nenhum registro encontrado.", "").toJson()
                : new OperationResult(StatusRetorno.OPERACAO_OK, result.size() + " registros encontrados.", "").toJson());
    }
}
