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
import interfaces.ICargo;
import java.util.List;
import model.Cargo;

/**
 *
 * @author Marcos Vinícius
 */
public class CargoRepository extends RepositoryImpl<Cargo> implements ICargo
{

    @Override
    public List<Cargo> search(String search)
    {
        Cargo cargo = new Cargo();
        createCriteria(cargo, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.like(FILTER_TYPE.WHERE, "descricao", search, MATCH_MODE.ANYWHERE))
                .execute();
        return cargo.toList();
    }
    
}
