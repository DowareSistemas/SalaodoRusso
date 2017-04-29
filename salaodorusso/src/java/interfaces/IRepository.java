/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.interfaces.ICriteria;
import br.com.persistor.interfaces.Session;
import br.com.persistor.sessionManager.Query;

/**
 *
 * @author Marcos Vinícius
 * @param <T>
 */
public interface IRepository<T>
{

    void setSession(Session session);

    Session getSession();

    void setAutoCommitOrClose(boolean value);

    void save(T entity);

    void update(T entity);

    void remove(T entity);

    T find(Class entityClass, int id);

    boolean exists(Class entityClass, int id);

    void commit(boolean close);

    void close();

    Query createQuery(T entity, String query);

    ICriteria createCriteria(T entity, RESULT_TYPE result_type);
}
