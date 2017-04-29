/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Usuario;

/**
 *
 * @author Marcos Vinícius
 */
public interface IUsuario extends IRepository<Usuario>
{

    public List<Usuario> listAll();

    public Usuario efetuaLogin(String email, String senha);
    
}
