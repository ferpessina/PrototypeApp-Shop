package fer.shop.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import fer.shop.resource.HibernateSessionManager;
import fer.shop.dao.UserDao;
import fer.shop.entity.User;

@Service
public class UserDaoImpl extends GenericDaoImpl<User, Long>implements UserDao{

	public User findByUserEmail(String userEmail) {
		System.out.println("Attempting to find user by email "+userEmail);
		User user = null;
		String sql = "select t from User t where t.userEmail = :email";
		Query query = HibernateSessionManager.getSession().createQuery(sql).setParameter("email", userEmail);
		System.out.println("Attempting to enter findOne");
		user = findOne(query);
		return user;
	}

}
