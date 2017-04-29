/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.IDica;
import java.util.List;
import model.Dica;
import repository.DicaRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class DicaDao
{

    private IDica db = null;

    public DicaDao(boolean... autoCommit)
    {
        db = new DicaRepository();
        if (autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }

    public void commit(boolean close)
    {
        db.commit(close);
    }
    
    public boolean save(Dica dica)
    {
        if (db.exists(Dica.class, dica.getId()))
            db.update(dica);
        else
            db.save(dica);

        return (dica.saved || dica.updated);
    }
    
    public Dica find(int id)
    {
        return db.find(Dica.class, id);
    }
    
    public boolean remove(Dica dica)
    {
        db.remove(dica);
        return dica.deleted;
    }
    
    public List<Dica> search(String search, int current_page, int max_reccors)
    {
        return db.search(search, current_page, max_reccors);
    }
}
