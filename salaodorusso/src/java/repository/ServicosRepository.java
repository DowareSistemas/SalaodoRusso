/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import interfaces.IServicos;
import java.util.List;
import model.Servicos;

/**
 *
 * @author Marcos Vinícius
 */
public class ServicosRepository extends RepositoryImpl<Servicos> implements IServicos
{

    public List<Servicos> listAll()
    {
        return null;
    }

    public List<Servicos> search(String searchTerm)
    {
        return null;
    }

}
