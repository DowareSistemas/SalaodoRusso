/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.interfaces.ICriteria;
import br.com.persistor.interfaces.Session;
import br.com.persistor.sessionManager.Query;
import commons.SessionProvider;
import interfaces.IRepository;

/**
 *
 * @author Marcos Vinícius
 * @param <T>
 */
public abstract class RepositoryImpl<T> implements IRepository<T>
{

    private Session mainSession = null;
    private boolean autoCommitOrClose;

    private void checkInitialization()
    {
        try
        {
            if (mainSession == null)
                mainSession = SessionProvider.openSession();
            if (mainSession.getActiveConnection().isClosed())
                mainSession = SessionProvider.openSession();
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void save(T entity)
    {
        checkInitialization();
        mainSession.save(entity);

        if (autoCommitOrClose)
            commit(true);
    }

    @Override
    public void update(T entity)
    {
        checkInitialization();
        mainSession.update(entity);

        if (autoCommitOrClose)
            commit(true);
    }

    @Override
    public void remove(T entity)
    {
        checkInitialization();
        mainSession.delete(entity);

        if (autoCommitOrClose)
            commit(true);
    }

    @Override
    public T find(Class entityClass, int id)
    {
        checkInitialization();
        T entity = mainSession.onID(entityClass, id);
        if (autoCommitOrClose)
            close();
        return entity;
    }

    @Override
    public boolean exists(Class entityClass, int id)
    {
        checkInitialization();
        int count = mainSession.count(entityClass, "id = " + id);
        return (count > 1);
    }

    @Override
    public void commit(boolean close)
    {
        mainSession.commit();
        if (close)
            close();
    }

    @Override
    public void close()
    {
        mainSession.close();
        mainSession = null;
    }

    @Override
    public Query createQuery(T entity, String queryString)
    {
        checkInitialization();
        Query query = mainSession.createQuery(entity, queryString);
        query.setCloseSessionAfterExecute(autoCommitOrClose);

        return query;
    }

    @Override
    public ICriteria createCriteria(T entity, RESULT_TYPE result_type)
    {
        checkInitialization();
        ICriteria criteria = mainSession.createCriteria(entity, result_type);
        if (autoCommitOrClose)
            criteria.enableCloseSessionAfterExecute();

        return criteria;
    }

    @Override
    public void setSession(Session session)
    {
        this.mainSession = session;
    }

    @Override
    public Session getSession()
    {
        checkInitialization();
        return mainSession;
    }

    @Override
    public void setAutoCommitOrClose(boolean value)
    {
        this.autoCommitOrClose = value;
    }
}
