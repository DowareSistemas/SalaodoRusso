/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.validation.Valid;
import model.Formas_pagamento;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.Formas_pagamentoRepository;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class Formas_pagamentoController
{

    Formas_pagamentoRepository db = new Formas_pagamentoRepository();

    @RequestMapping(value = "/fpg-save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public @ResponseBody
    String save(@Valid Formas_pagamento fpg, BindingResult result)
    {
        if (result.hasErrors())
            return new OperationResult(StatusRetorno.FALHA_VALIDACAO, result.getFieldErrors().get(0).getDefaultMessage(), "").toJson();

        if (db.exists(Formas_pagamento.class, "id", fpg.getId()))
            db.update(fpg);
        else
            db.save(fpg);

        db.commit(true);

        return (fpg.saved || fpg.updated
                ? new OperationResult(StatusRetorno.OPERACAO_OK, "Forma de pagamento salva.", "").toJson()
                : new OperationResult(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar a forma de pagamento.", "").toJson());
    }

    @RequestMapping(value = "/fpg-rem", produces = "application/json; charset-utf-8")
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        Formas_pagamento fpg = db.find(Formas_pagamento.class, id);
        
        if(fpg.getId() == 0)
        {
            db.close();
            return new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado.", "").toJson();
        }
        
        db.delete(fpg);
        db.commit(true);
        
        return (fpg.deleted
                ? new OperationResult(StatusRetorno.OPERACAO_OK, "Forma de pagamento removida.", "").toJson()
                : new OperationResult(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao remover a forma de pagamento.", "").toJson());
    }
    
    @RequestMapping(value = "/fpg-search", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String search(@RequestParam(value = "query") String searchTerm, @RequestParam(value = "permite_inativo") boolean permite_inativo)
    {
        List<Formas_pagamento> result = 
                (searchTerm.isEmpty()
                ? db.listAll(permite_inativo)
                : db.search(searchTerm, permite_inativo));
        
        return (result.isEmpty()
                ? new OperationResult(StatusRetorno.NAO_ENCONTRADO, "Nenhum registro encontrado.", "").toJson()
                : new OperationResult(StatusRetorno.OPERACAO_OK, result.size() + " registros encontrados.", "").toJson());
    }
}

