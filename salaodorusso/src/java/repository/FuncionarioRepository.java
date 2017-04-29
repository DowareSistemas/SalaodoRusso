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
import interfaces.IFuncionario;
import java.util.List;
import model.Funcionario;

/**
 *
 * @author Marcos Vinícius
 */
public class FuncionarioRepository extends RepositoryImpl<Funcionario> implements IFuncionario
{

    @Override
    public List<Funcionario> search(String search)
    {
        Funcionario funcionario = new Funcionario();
        createCriteria(funcionario, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.like(FILTER_TYPE.WHERE, "nome", search, MATCH_MODE.ANYWHERE))
                .execute();
        return funcionario.toList();
    }

}
