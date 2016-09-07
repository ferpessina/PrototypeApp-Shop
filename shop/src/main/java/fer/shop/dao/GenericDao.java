package fer.shop.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

public interface GenericDao<T, ID extends Serializable> {

	void save(T entity);

	void update(T entity);

	void delete(T entity);

	List<T> findMany(Query query);

	T findOne(Query query);

	T findByID(Class<?> clazz, Long id);

	List<T> findAll(Class<?> clazz);

}