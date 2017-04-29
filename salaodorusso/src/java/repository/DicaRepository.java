/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import br.com.persistor.enums.FILTER_TYPE;
import br.com.persistor.enums.MATCH_MODE;
import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.generalClasses.Limit;
import br.com.persistor.generalClasses.Restrictions;
import interfaces.IDica;
import java.util.List;
import model.Dica;

/**
 *
 * @author Marcos Vinícius
 */
public class DicaRepository extends RepositoryImpl<Dica> implements IDica
{

    @Override
    public List<Dica> search(String search, int current_page, int max_reccors)
    {
        Dica dicas = new Dica();
        createCriteria(dicas, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.like(FILTER_TYPE.WHERE, "descricao", search, MATCH_MODE.ANYWHERE))
                .add(Restrictions.like(FILTER_TYPE.OR, "titulo", search, MATCH_MODE.ANYWHERE))
                .addLimit(Limit.paginate(current_page, max_reccors))
                .execute();
        return dicas.toList();
    }

}
