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
import br.com.persistor.interfaces.Session;
import controller.SessionProvider;
import interfaces.IEmpresa;
import java.util.List;
import model.Empresa;

/**
 *
 * @author Marcos Vinícius
 */
public class EmpresaRepository extends RepositoryImpl<Empresa> implements IEmpresa
{

    public List<Empresa> listAll()
    {
        Empresa emp = new Empresa();

        Session session = SessionProvider.openSession();
        session.createCriteria(emp, RESULT_TYPE.MULTIPLE)
                .execute();
        session.close();

        return session.getList(emp);
    }

    public List<Empresa> search(String searchTerm)
    {
        Empresa emp = new Empresa();

        Session session = SessionProvider.openSession();
        session.createCriteria(emp, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.like(FILTER_TYPE.WHERE, "nome", searchTerm, MATCH_MODE.ANYWHERE))
                .execute();
        session.close();
        
        return session.getList(emp);
    }
}
