package fer.shop.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;

import fer.shop.entity.User;

@Repository
@Transactional
public interface UserDao extends GenericDao<User, Long>{
	
	public User findByUserEmail(String userEmail);
}