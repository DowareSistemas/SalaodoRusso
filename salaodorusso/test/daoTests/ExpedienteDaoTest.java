/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTests;

import dao.ExpedienteDao;
import model.Expediente;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Marcos Vinícius
 */
public class ExpedienteDaoTest
{

    private Expediente getExpediente()
    {
        Expediente e = new Expediente();
        e.setTitulo("Expediente 1");
        e.setSegunda("08:00 18:00");
        e.setTerca("13:00 18:00");
        e.setQuarta("15:00 18:30");
        e.setQuinta(null);
        e.setSexta(null);
        e.setSabado("08:00 12:30");

        return e;
    }

    @Test
    public void DEVE_SALVAR_UM_EXPEDIENTE()
    {
        Expediente e = getExpediente();
        ExpedienteDao dao = new ExpedienteDao();
        boolean result = dao.save(e);
        dao.remove(e);
        dao.commit(true);

        assertTrue(result);
    }

    @Test
    public void DEVE_REMOVER_UM_EXPEDIENTE()
    {
        Expediente e = getExpediente();
        ExpedienteDao dao = new ExpedienteDao(false);
        dao.save(e);
        dao.commit(false);

        boolean result = dao.remove(e);
        dao.commit(true);

        assertTrue(result);
    }

    @Test
    public void DEVE_BUSCAR_EXPEDIENTE_POR_ID()
    {
        Expediente e = getExpediente();
        ExpedienteDao dao = new ExpedienteDao();
        dao.save(e);
        dao.commit(false);
        
        e = dao.find(e.getId());
        
        dao.remove(e);
        dao.commit(true);
        
        boolean result = (e.getId() > 0);
        assertTrue(result);
    }
}
