/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.persistor.interfaces.Session;
import interfaces.ICargo;
import java.util.List;
import model.Cargo;
import repository.CargoRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class CargoDao
{

    private ICargo db = null;

    public CargoDao(boolean... autoCommit)
    {
        db = new CargoRepository();
        if (autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }

    public boolean save(Cargo cargo)
    {
        if (db.exists(Cargo.class, cargo.getId()))
            db.update(cargo);
        else
            db.save(cargo);

        return (cargo.saved || cargo.updated);
    }

    public boolean remove(Cargo cargo)
    {
        db.remove(cargo);
        return cargo.deleted;
    }
    
    public Cargo find(int id)
    {
        return db.find(Cargo.class, id);
    }
    
    public List<Cargo> search(String search)
    {
        return db.search(search);
    }

    public void commit(boolean close)
    {
        db.commit(close);
    }

    void setSession(Session session)
    {
        db.setSession(session);
    }
}
