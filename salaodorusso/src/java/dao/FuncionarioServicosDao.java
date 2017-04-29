/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.IFuncionarioServicos;
import model.FuncionarioServicos;
import repository.FuncionarioServicosRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class FuncionarioServicosDao
{

    private IFuncionarioServicos db = null;

    public FuncionarioServicosDao(boolean... autoCommit)
    {
        db = new FuncionarioServicosRepository();
        if (autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }

    public boolean save(FuncionarioServicos fs)
    {
        db.save(fs);
        return (fs.saved);
    }

    public boolean remove(FuncionarioServicos fs)
    {
        db.remove(fs);
        return (fs.deleted);
    }

    public FuncionarioServicos find(int id)
    {
        return db.find(FuncionarioServicos.class, id);
    }
}
