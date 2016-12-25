/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import br.com.persistor.enums.FILTER_TYPE;
import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.generalClasses.Restrictions;
import br.com.persistor.interfaces.ICriteria;
import br.com.persistor.interfaces.Session;
import controller.SessionProvider;
import interfaces.IFormas_pagamento;
import java.util.List;
import model.Formas_pagamento;

/**
 *
 * @author Marcos Vinícius
 */
public class Formas_pagamentoRepository extends RepositoryImpl<Formas_pagamento> implements IFormas_pagamento
{

    public List<Formas_pagamento> search(String searchTerm, boolean permite_inativo)
    {
        Formas_pagamento fpg = new Formas_pagamento();

        Session session = SessionProvider.openSession();
        ICriteria c = session.createCriteria(fpg, RESULT_TYPE.MULTIPLE);
        if (permite_inativo)
            c.add(Restrictions.in(FILTER_TYPE.WHERE, "inativo", new String[]
            {
                "0", "1"
            }));
        else
            c.add(Restrictions.eq(FILTER_TYPE.WHERE, "inativo", "0"));
        c.add(Restrictions.eq(FILTER_TYPE.AND, "descricao", searchTerm));
        
        c.execute();
        session.close();

        return session.getList(fpg);
    }

    public List<Formas_pagamento> listAll(boolean permite_inativo)
    {
        Formas_pagamento fpg = new Formas_pagamento();

        Session session = SessionProvider.openSession();
        ICriteria c = session.createCriteria(fpg, RESULT_TYPE.MULTIPLE);
        if (permite_inativo)
            c.add(Restrictions.in(FILTER_TYPE.WHERE, "inativo", new String[]
            {
                "0", "1"
            }));
        else
            c.add(Restrictions.eq(FILTER_TYPE.WHERE, "inativo", "0"));
        c.execute();
        session.close();

        return session.getList(fpg);
    }

}
