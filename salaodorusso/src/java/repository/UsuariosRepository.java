/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import br.com.persistor.enums.FILTER_TYPE;
import br.com.persistor.enums.MATCH_MODE;
import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.generalClasses.Restrictions;
import br.com.persistor.interfaces.ICriteria;
import br.com.persistor.interfaces.Session;
import controller.SessionProvider;
import interfaces.IUsuarios;
import java.util.List;
import model.Usuarios;

/**
 *
 * @author Marcos Vinícius
 */
public class UsuariosRepository extends RepositoryImpl<Usuarios> implements IUsuarios
{

    public Usuarios efetuaLogin(String nome, String senha)
    {
        Usuarios usuario = new Usuarios();

        Session session = SessionProvider.openSession();
        session.createCriteria(usuario, RESULT_TYPE.UNIQUE)
                .add(Restrictions.eq(FILTER_TYPE.WHERE, "nome", nome))
                .add(Restrictions.eq(FILTER_TYPE.AND, "senha", senha))
                .execute();
        session.close();

        return usuario;
    }

    public List<Usuarios> search(String searchTerm, int tipo)
    {
        Usuarios usuarios = new Usuarios();
        Session session = SessionProvider.openSession();
        ICriteria c = session.createCriteria(usuarios, RESULT_TYPE.MULTIPLE);
     
        c.beginPrecedence();
        c.add(Restrictions.eq(FILTER_TYPE.WHERE, "tipo_usuario", tipo));
        c.endPrecedence();
        
        c.beginPrecedence();
        c.add(Restrictions.like(FILTER_TYPE.AND, "nome", searchTerm, MATCH_MODE.ANYWHERE));
        c.add(Restrictions.like(FILTER_TYPE.OR, "email", searchTerm, MATCH_MODE.ANYWHERE));
        c.add(Restrictions.like(FILTER_TYPE.OR, "celular", searchTerm, MATCH_MODE.ANYWHERE));
        c.add(Restrictions.like(FILTER_TYPE.OR, "telefone", searchTerm, MATCH_MODE.ANYWHERE));
        c.endPrecedence();
        
        c.execute();
        session.close();

        return session.getList(usuarios);
    }

    public List<Usuarios> listAll(int tipo)
    {
        Usuarios usuarios = new Usuarios();
        Session session = SessionProvider.openSession();
        session.createCriteria(usuarios, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.eq(FILTER_TYPE.WHERE, "tipo_usuario", tipo))
                .execute();
        session.close();

        return session.getList(usuarios);
    }
}
