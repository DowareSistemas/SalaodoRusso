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
import interfaces.ITipoServico;
import java.util.List;
import model.TipoServico;

/**
 *
 * @author Marcos Vinícius
 */
public class TipoServicoRepository extends RepositoryImpl<TipoServico> implements ITipoServico
{

    @Override
    public List<TipoServico> search(String search)
    {
        TipoServico tipoServico = new TipoServico();
        createCriteria(tipoServico, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.like(FILTER_TYPE.WHERE, "descricao", search, MATCH_MODE.ANYWHERE))
                .execute();
        return tipoServico.toList();
    }
    
}
