/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Funcionario;

/**
 *
 * @author Marcos Vinícius
 */
public interface IFuncionario extends IRepository<Funcionario> 
{

    public List<Funcionario> search(String search);
    
}
