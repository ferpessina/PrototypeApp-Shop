package fer.shop.dao;
import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import fer.shop.entity.Product;
import fer.shop.entity.User;

@Transactional
public interface ProductDao extends CrudRepository<Product,Long>{
	public List<Product> findByOwner(User owner);
}
