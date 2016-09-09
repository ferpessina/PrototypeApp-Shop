package fer.shop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import fer.shop.entity.Product;

@Transactional
public interface ProductDao extends CrudRepository<Product, Long> {
	public List<Product> findByName(String name);
}
