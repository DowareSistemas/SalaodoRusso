/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import br.com.persistor.enums.FILTER_TYPE;
import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.generalClasses.Restrictions;
import interfaces.IExpediente;
import java.util.List;
import model.Expediente;

/**
 *
 * @author Marcos Vinícius
 */
public class ExpedienteRepository extends RepositoryImpl<Expediente> implements IExpediente
{

    @Override
    public List<Expediente> listByFuncionario(int funcionarioId)
    {
        Expediente expediente = new Expediente();
        createCriteria(expediente, RESULT_TYPE.MULTIPLE)
                .add(Restrictions.eq(FILTER_TYPE.WHERE, "funcionario_id", funcionarioId))
                .execute();
        return expediente.toList();
    }
    
    @Override
    public List<Expediente> listAll()
    {
        Expediente expediente = new Expediente();
        createCriteria(expediente, RESULT_TYPE.MULTIPLE)
                .execute();
        return expediente.toList();
    }
        
    
}
