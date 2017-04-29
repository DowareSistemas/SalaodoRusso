/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.persistor.interfaces.Session;
import interfaces.IEndereco;
import model.Endereco;
import repository.EnderecoRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class EnderecoDao
{

    private IEndereco db = null;

    public EnderecoDao(boolean... autoCommit)
    {
        db = new EnderecoRepository();
        if (autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }

    public boolean save(Endereco endereco)
    {
        if (db.exists(Endereco.class, endereco.getId()))
            db.update(endereco);
        else
            db.save(endereco);

        return (endereco.saved || endereco.updated);
    }

    public Endereco find(int id)
    {
        return db.find(Endereco.class, id);
    }

    public boolean remove(Endereco endereco)
    {
        db.remove(endereco);
        return endereco.deleted;
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
