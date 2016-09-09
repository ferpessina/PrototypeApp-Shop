package fer.shop.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import fer.shop.dao.ProductDao;
import fer.shop.entity.Product;
import java.util.*;

@Service
public class ProductDaoImpl extends GenericDaoImpl<Product, Long>implements ProductDao{

	public List<Product> findByName(String name) {
		List<Product> products = null;
		String sql = "select p from Product p where p.productName = :name";
		Query query = getSession().createQuery(sql).setParameter("name", name);
		products = findMany(query);
		return products;
	}
}
