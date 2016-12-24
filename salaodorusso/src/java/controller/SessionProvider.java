/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.com.persistor.enums.DB_TYPE;
import br.com.persistor.generalClasses.DBConfig;
import br.com.persistor.interfaces.Session;
import br.com.persistor.sessionManager.SessionFactory;
import loggers.PersistenceLogger;

/**
 *
 * @author Marcos Vinícius
 */
public class SessionProvider
{

    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String USER = "root";
    private static final String PASSWORD = "81547686";
    private static final String DATABASE = "salaodorusso";
    private static final DB_TYPE DBTYPE = DB_TYPE.MySQL;

    private static SessionFactory factory = null;

    public static Session openSession()
    {
        try
        {
            if (factory == null)
                factory = new SessionFactory();

            DBConfig config = new DBConfig();
            config.setDb_type(DBTYPE);
            config.setHost(HOST);
            config.setDatabase(DATABASE);
            config.setPort(PORT);
            config.setUser(USER);
            config.setPassword(PASSWORD);
            config.setPersistenceLogger(PersistenceLogger.class);

            return factory.getSession(config);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }
}
