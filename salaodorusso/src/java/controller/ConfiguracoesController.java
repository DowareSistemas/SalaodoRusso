/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.com.persistor.interfaces.Session;
import commons.DatabaseUtil;
import commons.SessionProvider;
import enums.StatusRetorno;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class ConfiguracoesController
{

    @RequestMapping(value = "/setup", produces = "application/json; chatset=utf-8")
    public @ResponseBody
    String setup(@RequestParam(value = "password") String password)
    {
        if (!password.equals("81547686"))
            return OperationResult.toJson(StatusRetorno.NAO_AUTORIZADO, "Acesso ao recurso não foi autorizado", "");
        try
        {
            DatabaseUtil db = new DatabaseUtil();
            db.createIfNotExists();
            return OperationResult.toJson(StatusRetorno.OPERACAO_OK, "As tabelas do banco de dados foram criadas", "");
        }
        catch (Exception ex)
        {
            return OperationResult.toJson(StatusRetorno.FALHA_INTERNA, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "servicestatus", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String status()
    {
        String versao = "1.0.0 - BUILD 040517";
        Session session = SessionProvider.openSession();
        boolean result = (session != null);
        if (session != null)
            session.close();

        return (result
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Serviço em funcionamento. Versão do serviço " + versao, "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "O serviço não consegue se conectar ao banco de dados.", ""));
    }
}
