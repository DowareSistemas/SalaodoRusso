/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.ITipoServico;
import java.util.List;
import model.TipoServico;
import repository.TipoServicoRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class TipoServicoDao
{

    private ITipoServico db = null;

    public TipoServicoDao(boolean... autoCommit)
    {
        db = new TipoServicoRepository();
        if (autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }
    
    public boolean save(TipoServico tipoServico)
    {
        if(db.exists(TipoServico.class, tipoServico.getId()))
            db.update(tipoServico);
        else
            db.save(tipoServico);
        
        return (tipoServico.saved || tipoServico.updated);
    }
    
    public TipoServico find(int id)
    {
        return db.find(TipoServico.class, id);
    }
    
    public boolean remove(TipoServico tipoServico)
    {
        db.remove(tipoServico);
        return tipoServico.deleted;
    }
    
    public List<TipoServico> search(String search)
    {
        return db.search(search);
    }

    public void commit(boolean close)
    {
        db.commit(close);
    }
}
