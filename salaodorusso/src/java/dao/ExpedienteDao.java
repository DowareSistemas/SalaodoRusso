/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.persistor.interfaces.Session;
import interfaces.IExpediente;
import java.util.List;
import model.Expediente;
import repository.ExpedienteRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class ExpedienteDao
{
    private IExpediente db = null;
    
    public ExpedienteDao(boolean... autoCommit)
    {
        db = new ExpedienteRepository();
        if(autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }
    
    public boolean save(Expediente expediente)
    {
        if(db.exists(Expediente.class, expediente.getId()))
            db.update(expediente);
        else
            db.save(expediente);
        
        return (expediente.saved || expediente.updated);
    }
    
    public boolean remove(Expediente expediente)
    {
        db.remove(expediente);
        return expediente.deleted;
    }
    
    public Expediente find(int id)
    {
        return db.find(Expediente.class, id);
    }
    
    public List<Expediente> listByFuncionario(int funcionarioId)
    {
        return db.listByFuncionario(funcionarioId);
    }

    void setSession(Session session)
    {
        db.setSession(session);
    }

    public void commit(boolean close)
    {
       db.commit(close);
    }
    
    public List<Expediente> listAll()
    {
        return db.listAll();
    }
}
