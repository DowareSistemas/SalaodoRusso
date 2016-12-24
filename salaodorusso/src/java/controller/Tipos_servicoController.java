/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.validation.Valid;
import model.Tipos_servicos;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.Tipos_servicoRepository;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class Tipos_servicoController
{

    Tipos_servicoRepository db = new Tipos_servicoRepository();

    @RequestMapping(value = "tserv-save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public @ResponseBody
    String save(@Valid Tipos_servicos tipo, BindingResult result)
    {
        if (result.hasErrors())
            return new OperationResult(StatusRetorno.FALHA_VALIDACAO, result.getFieldErrors().get(0).getDefaultMessage(), "").toJson();

        if (db.exists(Tipos_servicos.class, "id", tipo.getId()))
            db.update(tipo);
        else
            db.save(tipo);

        return (tipo.saved || tipo.updated
                ? new OperationResult(StatusRetorno.OPERACAO_OK, "Salvo.", "").toJson()
                : new OperationResult(StatusRetorno.FALHA_INTERNA, "Problema ao salvar", "").toJson());
    }

    @RequestMapping(value = "tserv-find", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
       db.enableAutoCommitAndClose();
       Tipos_servicos t = db.find(Tipos_servicos.class, id);
       
       return (t.getId() == 0
               ? new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "").toJson()
               : new OperationResult(StatusRetorno.OPERACAO_OK, "", t).toJson());
    }
    
    @RequestMapping(value = "tserv-rem", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        if(!db.exists(Tipos_servicos.class, "id", id))
            return new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado.", "").toJson();
        
        Tipos_servicos t = new Tipos_servicos();
        db.delete(t);
        db.commit(true);
        
        return (t.deleted
                ? new OperationResult(StatusRetorno.OPERACAO_OK, "Registro removido.", "").toJson()
                : new OperationResult(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao remover o tipo de serviço.", "").toJson());
    }
    
    @RequestMapping(value = "tserv-search", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String search(@RequestParam(value = "query") String searchTerm, @RequestParam(value = "incluir_inativos") boolean permiteInativos)
    {
        List<Tipos_servicos> list = (searchTerm.isEmpty()
                ? db.listAll(permiteInativos)
                : db.search(searchTerm, permiteInativos));

        return (list.isEmpty()
                ? new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Nenhum registro encontrado.", "").toJson()
                : new OperationResult(StatusRetorno.OPERACAO_OK, list.size() + " registros encontrados.", list).toJson());
    }
}
