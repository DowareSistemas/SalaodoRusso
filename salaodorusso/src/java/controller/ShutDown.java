/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.com.persistor.interfaces.Session;
import commons.SessionProvider;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Marcos Vinícius
 */
public class ShutDown implements ServletContextListener
{

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("*** iniciou ***");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        Session session = SessionProvider.openSession();
        session.getSLPersistenceContext().shutDownCacheManager();
        session.close();
    }
    
}
