package fer.shop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import fer.shop.entity.Product;

@Repository
public interface ProductDao extends GenericDao<Product, Long>{
	public List<Product> findByName(String name);
}
