/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggers;

import br.com.persistor.generalClasses.PersistenceLog;
import br.com.persistor.interfaces.IPersistenceLogger;

/**
 *
 * @author Marcos Vinícius
 */
public class PersistenceLogger implements  IPersistenceLogger
{

    @Override
    public void newNofication(PersistenceLog pl)
    {
        System.err.println(pl.getDescription());
    }
    
}
