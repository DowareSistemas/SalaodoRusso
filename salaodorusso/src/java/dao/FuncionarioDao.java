/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.persistor.interfaces.Session;
import interfaces.IFuncionario;
import java.util.List;
import model.Funcionario;
import repository.FuncionarioRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class FuncionarioDao
{

    private IFuncionario db = null;

    public FuncionarioDao(boolean... autoCommit)
    {
        db = new FuncionarioRepository();
        if (autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }

    public boolean save(Funcionario funcionario)
    {
        if (db.exists(Funcionario.class, funcionario.getId()))
            db.update(funcionario);
        else
            db.save(funcionario);

        return (funcionario.saved || funcionario.updated);
    }

    public Funcionario find(int id)
    {
        return db.find(Funcionario.class, id);
    }

    public Session getSession()
    {
        return db.getSession();
    }

    public boolean remove(Funcionario funcionario)
    {
        db.setAutoCommitOrClose(false);

        if (funcionario.getEndereco() != null)
        {
            EnderecoDao enderecoDao = new EnderecoDao(false);
            enderecoDao.setSession(getSession());

            enderecoDao.remove(funcionario.getEndereco());
        }

        db.remove(funcionario);
        return funcionario.deleted;
    }

    public List<Funcionario> search(String search)
    {
        return db.search(search);
    }

    public void commit(boolean close)
    {
        db.commit(close);
    }
}
