package fer.shop.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fer.shop.resource.HibernateSessionManager;

public class GenericDao<T, ID extends Serializable>{
	
    protected Session getSession() {
        return HibernateSessionManager.getSessionFactory().getCurrentSession();
    }
    
	public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);
    }
 
    @SuppressWarnings("unchecked")
	public void update(T entity) {
        Session hibernateSession = this.getSession();
        entity = (T) hibernateSession.merge(entity);
        hibernateSession.saveOrUpdate(entity);
    }
 
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
 
    @SuppressWarnings("unchecked")
	public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }
 
    @SuppressWarnings("unchecked")
	public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
    
    @SuppressWarnings("unchecked")
	public T findByID(Class<?> clazz, Long id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        return t;
    }
 
    @SuppressWarnings("unchecked")
	public List<T> findAll(Class<?> clazz) {
        Session hibernateSession = this.getSession();
        List<T> T = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        T = query.list();
        return T;
    }
}
