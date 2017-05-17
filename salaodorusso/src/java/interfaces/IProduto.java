/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Produto;

/**
 *
 * @author Marcos Vinícius
 */
public interface IProduto extends IRepository<Produto>
{

    public List<Produto> search(String search);

    public List<Produto> top8();
    
}
