package fer.shop.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import fer.shop.entity.User;

@Transactional
public interface UserDao extends CrudRepository<User,Long>{
	
	public User findByUserEmail(String userEmail);
}