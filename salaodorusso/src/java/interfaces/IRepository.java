/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Marcos Vinícius
 */
public interface IRepository<T>
{

    void save(T entity);

    void update(T entity, String... whereCondition);

    void delete(T entity, String... whereCondition);

    T find(Class entityClass, int id);

    T first(Class entityClass, String... whereCondition);

    T last(Class entityClass, String... whereCondition);

    void close();

    void commit(boolean close);
    
    boolean exists(Class entityClass, String field, Object value);
}
