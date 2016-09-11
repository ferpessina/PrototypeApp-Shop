package fer.shop.dao;

import org.springframework.data.repository.CrudRepository;

import fer.shop.entity.Category;

public interface CategoryDao extends CrudRepository<Category,Long>{
	
}
