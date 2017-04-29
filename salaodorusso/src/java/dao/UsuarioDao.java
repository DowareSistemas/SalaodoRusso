/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.persistor.interfaces.Session;
import interfaces.IUsuario;
import java.util.List;
import model.Usuario;
import repository.UsuarioRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class UsuarioDao
{

    private IUsuario db = null;

    public UsuarioDao(boolean... autoCommit)
    {
        db = new UsuarioRepository();
        if (autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }

    public boolean save(Usuario usuario)
    {
        if (db.exists(Usuario.class, usuario.getId()))
            db.update(usuario);
        else
            db.save(usuario);

        return (usuario.saved || usuario.updated);
    }

    public Usuario find(int id)
    {
        return db.find(Usuario.class, id);
    }

    public Session getSession()
    {
        return db.getSession();
    }

    public void setSession(Session session)
    {
        db.setSession(session);
    }

    public boolean remove(Usuario usuario)
    {
        if (usuario.getEndereco_id() > 0)
        {
            EnderecoDao enderecoDao = new EnderecoDao(false);
            enderecoDao.setSession(getSession());
            
            enderecoDao.remove(usuario.getEndereco());
        }

        db.remove(usuario);
        return usuario.deleted;
    }

    public Usuario efetuaLogin(String email, String senha)
    {
        return db.efetuaLogin(email, senha);
    }

    public List<Usuario> listAll()
    {
        return db.listAll();
    }

    public void commit(boolean close)
    {
        db.commit(close);
    }
}
