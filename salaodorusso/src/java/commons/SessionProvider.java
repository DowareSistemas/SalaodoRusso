/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import br.com.persistor.enums.DB_TYPE;
import br.com.persistor.generalClasses.DBConfig;
import br.com.persistor.interfaces.Session;
import br.com.persistor.sessionManager.SessionFactory;

/**
 *
 * @author Marcos Vinícius
 */
public class SessionProvider
{

    private static SessionFactory factory = null;

    public synchronized static Session openSession()
    {
        try
        {
            if (factory == null)
            {
                factory = new SessionFactory();
                return factory.getSession(getConfigHomologacao());
            }

            return factory.getSession();
        }
        catch (Exception ex)
        {
            System.err.println("*** ERRO AO ABRIR CONEXAO *** \n" + ex.getMessage());
            return null;
        }
    }

    public static DBConfig getConfigHomologacao()
    {
        DBConfig config = new DBConfig();
        config.setDb_type(DB_TYPE.MySQL);
        config.setDatabase("salaodorusso");
        config.setUser("root");
        config.setPassword("81547686");
        config.setHost("localhost");
        config.setPort(3306);
        config.setPersistenceLogger(PersistenceLoggerImpl.class);
        config.setMaxIdleTime(10);
        config.setMaxStatements(10);
        config.setMaxIdleTimeExcessConnections(10);

        return config;

    }

    public static DBConfig getConfigProducao()
    {
        DBConfig config = new DBConfig();
        config.setDb_type(DB_TYPE.MySQL);
        config.setDatabase("doware_salaodorusso");
        config.setUser("doware_AA4990");
        config.setPassword("doware_AA4990");
        config.setHost("localhost");
        config.setPort(3306);
        config.setPersistenceLogger(PersistenceLoggerImpl.class);
        config.setMaxIdleTime(10);
        config.setMaxStatements(10);
        config.setMaxIdleTimeExcessConnections(10);

        return config;
    }
}
