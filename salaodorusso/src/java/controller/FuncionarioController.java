/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.Utility;
import dao.FuncionarioDao;
import dao.FuncionarioServicosDao;
import enums.StatusRetorno;
import java.util.List;
import javax.validation.Valid;
import model.Funcionario;
import model.FuncionarioServicos;
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
public class FuncionarioController
{

    @RequestMapping(value = "/funcionarios-save", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String save(@Valid Funcionario funcionario, BindingResult result)
    {
        if (result.hasErrors())
            return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, Utility.getBindingResultMessage(result), "");

        FuncionarioDao dao = new FuncionarioDao(true);
        dao.save(funcionario);

        return (funcionario.saved || funcionario.updated
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Funcionário salvo com sucesso", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao salvar o funcionario.", ""));
    }

    @RequestMapping(value = "/funcionario-find", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String find(@RequestParam(value = "id") int id)
    {
        FuncionarioDao dao = new FuncionarioDao(true);
        Funcionario funcionario = dao.find(id);

        return (funcionario.getId() == 0
                ? OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "")
                : OperationResult.toJson(StatusRetorno.OPERACAO_OK, "", funcionario));
    }

    @RequestMapping(value = "/funcionario-remove", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String remove(@RequestParam(value = "id") int id)
    {
        FuncionarioDao dao = new FuncionarioDao(true);
        Funcionario funcionario = dao.find(id);

        if (funcionario.getId() == 0)
        {
            dao.commit(true);
            return OperationResult.toJson(StatusRetorno.NAO_ENCONTRADO, "Registro não encontrado", "");
        }

        dao.remove(funcionario);
        dao.commit(true);

        return (funcionario.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Funcionário removido", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao remover o funcionário", ""));
    }

    @RequestMapping(value = "/funcionario-search", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String search(@RequestParam(value = "search") String search)
    {
        FuncionarioDao dao = new FuncionarioDao(true);
        List<Funcionario> list = dao.search(search);

        return OperationResult.toJson(StatusRetorno.OPERACAO_OK, list.size() + " registros encontrados", list);
    }

    @RequestMapping(value = "/funcionario-incluirtiposervico", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String incluirTipoServico(
            @RequestParam(value = "funcionario_id") int funcionario_id,
            @RequestParam(value = "tiposervico_id") int tiposervico_id)
    {
        FuncionarioDao fDao = new FuncionarioDao(true);
        Funcionario funcionario = fDao.find(funcionario_id);

        List<FuncionarioServicos> list = funcionario.getFuncionarioServicos().toList();
        for (FuncionarioServicos funcionarioServ : list)
            if (funcionarioServ.getTiposervico_id() == tiposervico_id)
                return OperationResult.toJson(StatusRetorno.FALHA_VALIDACAO, "Este tipo de serviço já está anexado a este funcionário", "");

        FuncionarioServicos fs = new FuncionarioServicos();
        fs.setFuncionario_id(funcionario_id);
        fs.setTiposervico_id(tiposervico_id);

        FuncionarioServicosDao dao = new FuncionarioServicosDao(true);
        dao.save(fs);

        return (fs.saved
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "O tipo de serviço foi anexado ao funcionário com sucesso", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao anexar o tipo de serviço ao funcionário", ""));
    }

    @RequestMapping(value = "/funcionario-removertiposervico", method = RequestMethod.POST, produces = Utility.DEFAULT_PRODUCES_VALUE)
    public @ResponseBody
    String removerTipoServico(@RequestParam(value = "funcionarioservico_id") int funcionarioservico_id)
    {
        FuncionarioServicosDao dao = new FuncionarioServicosDao(false);
        FuncionarioServicos fs = dao.find(funcionarioservico_id);
        dao.remove(fs);
        dao.commit(true);

        return (fs.deleted
                ? OperationResult.toJson(StatusRetorno.OPERACAO_OK, "Tipo de serviço removido deste funcionário", "")
                : OperationResult.toJson(StatusRetorno.FALHA_INTERNA, "Ocorreu um problema ao remover o tipo de serviço deste funcionário", ""));
    }
}
