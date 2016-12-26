/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import br.com.persistor.enums.FILTER_TYPE;
import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.generalClasses.Restrictions;
import br.com.persistor.interfaces.Session;
import com.sun.xml.internal.org.jvnet.fastinfoset.RestrictedAlphabet;
import controller.SessionProvider;
import interfaces.IRepository;

/**
 *
 * @author Marcos Vinícius
 */
public abstract class RepositoryImpl<T> implements IRepository<T>
{

    private boolean autoCommit = false;

    public void enableAutoCommitAndClose()
    {
        this.autoCommit = true;
    }

    private Session session = null;

    private void checkInitialization()
    {
        try
        {
            if (session == null)
                session = SessionProvider.openSession();
            if (session.getActiveConnection().isClosed())
                session = SessionProvider.openSession();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(T entity)
    {
        checkInitialization();

        session.save(entity);
        if (autoCommit)
            commit(true);
    }

    @Override
    public void update(T entity, String... whereCondition)
    {
        checkInitialization();

        if (whereCondition != null)
        {
            if (whereCondition.length > 0)
                session.update(entity, whereCondition[0]);
        }
        else
            session.update(entity);
        if (autoCommit)
            commit(true);
    }

    @Override
    public void delete(T entity, String... whereCondition)
    {
        checkInitialization();

        if (whereCondition != null)
        {
            if (whereCondition.length > 0)
                session.delete(entity, whereCondition[0]);
        }
        else
            session.update(entity);
        if (autoCommit)
            commit(true);
    }

    @Override
    public T find(Class entityClass, int id)
    {
        checkInitialization();

        T entity = session.onID(entityClass, id);
        if (autoCommit)
            close();

        return entity;
    }

    @Override
    public T first(Class entityClass, String... whereCondition)
    {
        checkInitialization();

        T entity = null;

        if (whereCondition != null)
        {
            if (whereCondition.length > 0)
                entity = session.first(entityClass, whereCondition[0]);
        }
        else
            entity = session.first(entityClass, "");

        if (autoCommit)
            close();

        return entity;
    }

    @Override
    public T last(Class entityClass, String... whereCondition)
    {
        checkInitialization();

        T entity = null;
        if (whereCondition != null)
        {
            if (whereCondition.length > 0)
                entity = session.last(entityClass, whereCondition[0]);
        }
        else
            entity = session.last(entityClass, "");

        if (autoCommit)
            close();

        return entity;
    }

    @Override
    public void close()
    {
        session.close();
    }

    @Override
    public void commit(boolean close)
    {
        session.commit();
        if (close)
            close();
    }

    @Override
    public boolean exists(Class entityClass, String field, Object value)
    {
        try
        {
            checkInitialization();
            int count = session.count(entityClass, field + " = " + value);
            return (count > 0);
        }
        catch (Exception ex)
        {

        }
        return false;
    }
}
