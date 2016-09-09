package fer.shop.dao;


import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;

import fer.shop.entity.User;

@Repository
public interface UserDao extends GenericDao<User, Long>{
	
	public User findByUserEmail(String userEmail);
}