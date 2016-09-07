package fer.shop.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fer.shop.resource.HibernateSessionManager;
import fer.shop.dao.UserDao;
import fer.shop.entity.User;

@RestController
@EnableAutoConfiguration
public class ShopController {

	HibernateSessionManager sessionManager = new HibernateSessionManager();
	private UserDao userDao;
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping(value = "/test1", method = GET)
	public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new User("Fernando","Pessina","pessi14","fer.pessina1@gmail.com");
	}
	
	@RequestMapping(value = "/test2", method = GET)
	public String testMethod() {
		User user = new User("fernando", "pessina", "pessi14", "fer.pessina1@gmail.com");
		System.out.println("trying to create user");
		try{
			HibernateSessionManager.beginTransaction();
			userDao.save(user);
			HibernateSessionManager.commitTransaction();
			return "Success";
		}catch(HibernateException ex){
			System.out.println("Error in create User");
            HibernateSessionManager.rollbackTransaction();
			return "fail";
		}
	}


}
