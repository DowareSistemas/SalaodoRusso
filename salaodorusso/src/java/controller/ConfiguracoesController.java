/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import br.com.persistor.interfaces.Session;
import commons.SessionProvider;
import controller.OperationResult;
import enums.StatusRetorno;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class ConfiguracoesController
{
 
    @RequestMapping(value = "servicestatus", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String status()
    {
        String versao = "1.0.5 - BUILD 250417";
        Session session = SessionProvider.openSession();
        boolean result = (session != null);
        if (session != null)
            session.close();

        return (result
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Serviço em funcionamento. Versão do serviço " + versao, "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "O serviço não consegue se conectar ao banco de dados.", ""));
    }
}
