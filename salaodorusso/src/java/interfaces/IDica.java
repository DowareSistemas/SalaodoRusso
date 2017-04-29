/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Dica;

/**
 *
 * @author Marcos Vinícius
 */
public interface IDica extends IRepository<Dica>
{

    public List<Dica> search(String search, int current_page, int max_reccors);
    
}
