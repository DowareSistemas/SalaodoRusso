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
import interfaces.ITipos_servicos;
import java.util.List;
import model.Tipos_servicos;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcos Vinícius
 */
@Repository
public class Tipos_servicoRepository extends RepositoryImpl<Tipos_servicos> implements ITipos_servicos
{

    public List<Tipos_servicos> listAll(boolean permiteInativos)
    {
        String[] in = (permiteInativos
                ? new String[]
                {
                    "0", "1"
                }
                : new String[]
                {
                    "0"
                });

        Tipos_servicos t = new Tipos_servicos();
        Session session = SessionProvider.openSession();
        session.createCriteria(t, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.in(FILTER_TYPE.WHERE, "inativo", in))
                .execute();
        session.close();

        return session.getList(t);
    }

    public List<Tipos_servicos> search(String searchTerm, boolean permiteInativos)
    {
        String[] in = (permiteInativos
                ? new String[]
                {
                    "0", "1"
                }
                : new String[]
                {
                    "0"
                });

        Tipos_servicos t = new Tipos_servicos();
        Session session = SessionProvider.openSession();
        session.createCriteria(t, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.in(FILTER_TYPE.WHERE, "inativo", in))
                .add(Restrictions.like(FILTER_TYPE.AND, "descricao", searchTerm, MATCH_MODE.ANYWHERE))
                .execute();
        session.close();

        return session.getList(t);
    }

}
