/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.TipoServico;

/**
 *
 * @author Marcos Vinícius
 */
public interface ITipoServico extends IRepository<TipoServico>
{

    public List<TipoServico> search(String search);
    
}
