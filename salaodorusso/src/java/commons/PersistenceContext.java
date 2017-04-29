/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import br.com.persistor.generalClasses.EntitySet;
import br.com.persistor.interfaces.IPersistenceContext;
import model.*;

/**
 *
 * @author Marcos Vinícius
 */
public class PersistenceContext implements IPersistenceContext
{

    private EntitySet<Dica> dicas;

    public EntitySet<Dica> getDicas()
    {
        return dicas;
    }

    public void setDicas(EntitySet<Dica> dicas)
    {
        this.dicas = dicas;
    }

}
