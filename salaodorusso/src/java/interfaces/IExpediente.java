/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Expediente;

/**
 *
 * @author Marcos Vinícius
 */
public interface IExpediente extends IRepository<Expediente>
{

    public List<Expediente> listByFuncionario(int funcionarioId);

    public List<Expediente> listAll();
    
}
