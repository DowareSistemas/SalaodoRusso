/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTests;

import dao.CargoDao;
import dao.ExpedienteDao;
import dao.FuncionarioDao;
import java.util.List;
import model.Cargo;
import model.Expediente;
import model.Funcionario;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Marcos Vinícius
 */
public class FuncionarioDaoTest
{

    private Funcionario getFuncionario()
    {
        Cargo cargo = new Cargo();
        cargo.setDescricao("Cabeleireira");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Marcela Dantas");
        funcionario.setEmail("marcela@salaodorusso.com.br");
        funcionario.setTelefone("33472520");
        funcionario.setCelular("999452020");
        funcionario.setCargo(cargo);
        funcionario.setExpediente(getExpediente());

        return funcionario;
    }

    private Cargo getCargo()
    {
        Cargo cargo = new Cargo();
        cargo.setDescricao("Manicure");

        return cargo;
    }

    private Expediente getExpediente()
    {
        Expediente expediente = new Expediente();
        expediente.setTitulo("Expediente 1");
        expediente.setSegunda("08:00 15:30");
        expediente.setTerca("13:00 18:30");
        expediente.setQuarta("08:00 18:30");
        expediente.setQuinta(null);
        expediente.setSexta(null);
        expediente.setSabado(null);

        return expediente;
    }

    @Test
    public void DEVE_SALVAR_UM_FUNCIONARIO()
    {
        Funcionario funcionario = getFuncionario();
        FuncionarioDao dao = new FuncionarioDao();

        boolean result = dao.save(funcionario);
        dao.remove(funcionario);
        dao.commit(true);

        new CargoDao(true).remove(funcionario.getCargo());
        new ExpedienteDao(true).remove(funcionario.getExpediente());
        assertTrue(result);
    }

    @Test
    public void DEVE_BUSCAR_UM_FUNCIONARIO_PELA_ID()
    {
        Funcionario funcionario = getFuncionario();
        FuncionarioDao dao = new FuncionarioDao();
        dao.save(funcionario);
        dao.commit(false);

        funcionario = dao.find(funcionario.getId());

        dao.remove(funcionario);
        dao.commit(true);

        new CargoDao(true).remove(funcionario.getCargo());
        new ExpedienteDao(true).remove(funcionario.getExpediente());

        boolean result = (funcionario.getId() > 0);
        assertTrue(result);
    }

    @Test
    public void DEVE_PESQUISAR_FUNCIONARIOS()
    {
        Funcionario funcionario = getFuncionario();
        FuncionarioDao dao = new FuncionarioDao();

        dao.save(funcionario);
        dao.commit(false);

        List<Funcionario> list = dao.search("tas");

        dao.remove(funcionario);
        dao.commit(true);

        new CargoDao(true).remove(funcionario.getCargo());
        new ExpedienteDao(true).remove(funcionario.getExpediente());

        boolean result = (list.size() > 0 && list.get(0).getNome().contains("Dantas"));
        assertTrue(result);
    }

    @Test
    public void DEVE_REMOVER_FUNCIONARIO()
    {
        FuncionarioDao fDao = new FuncionarioDao();
        ExpedienteDao eDao = new ExpedienteDao();
        CargoDao cDao = new CargoDao();

        Cargo cargo = getCargo();
        Funcionario funcionario = getFuncionario();

        cDao.save(cargo);
        cDao.commit(false);

        funcionario.setCargo_id(cargo.getId());

        fDao.save(funcionario);
        fDao.commit(false);

        funcionario = fDao.find(funcionario.getId());
        boolean result = fDao.remove(funcionario);
        fDao.commit(true);

        cDao.remove(cargo);
        cDao.commit(true);

        eDao.remove(funcionario.getExpediente());
        eDao.commit(true);

        assertTrue(result);
    }
}
