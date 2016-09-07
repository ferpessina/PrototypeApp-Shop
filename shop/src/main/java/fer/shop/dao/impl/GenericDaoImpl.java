package fer.shop.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fer.shop.dao.GenericDao;
import fer.shop.resource.HibernateSessionManager;


public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID>{
	 
    protected Session getSession() {
        return HibernateSessionManager.getSessionFactory().getCurrentSession();
    }
 
    /* (non-Javadoc)
	 * @see fer.shop.dao.GenericDao#save(T)
	 */
    @Override
	public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);
    }
 
    /* (non-Javadoc)
	 * @see fer.shop.dao.GenericDao#update(T)
	 */
    @Override
	@SuppressWarnings("unchecked")
	public void update(T entity) {
        Session hibernateSession = this.getSession();
        entity = (T) hibernateSession.merge(entity);
        hibernateSession.saveOrUpdate(entity);
    }
 
    /* (non-Javadoc)
	 * @see fer.shop.dao.GenericDao#delete(T)
	 */
    @Override
	public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
 
    /* (non-Javadoc)
	 * @see fer.shop.dao.GenericDao#findMany(org.hibernate.Query)
	 */
    @Override
	@SuppressWarnings("unchecked")
	public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }
 
    /* (non-Javadoc)
	 * @see fer.shop.dao.GenericDao#findOne(org.hibernate.Query)
	 */
    @Override
	@SuppressWarnings("unchecked")
	public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
 
    /* (non-Javadoc)
	 * @see fer.shop.dao.GenericDao#findByID(java.lang.Class, java.lang.Long)
	 */
    @Override
	@SuppressWarnings("unchecked")
	public T findByID(Class<?> clazz, Long id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        return t;
    }
 
    /* (non-Javadoc)
	 * @see fer.shop.dao.GenericDao#findAll(java.lang.Class)
	 */
    @Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<?> clazz) {
        Session hibernateSession = this.getSession();
        List<T> T = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        T = query.list();
        return T;
    }
}