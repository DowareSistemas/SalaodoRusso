/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import br.com.persistor.generalClasses.PersistenceLog;
import br.com.persistor.interfaces.IPersistenceLogger;

/**
 *
 * @author Marcos Vinícius
 */
public class PersistenceLoggerImpl implements IPersistenceLogger
{

    @Override
    public void newNofication(PersistenceLog pl)
    {
        System.out.println("*** ERRO *** ");
        System.err.println("Class: " + pl.getClassName());
        System.err.println("Method: " + pl.getMethodName());
        System.err.println("Query: " + pl.getQuery());
        System.err.println("Exception: " + pl.getDescription());
       
    }
    
}
