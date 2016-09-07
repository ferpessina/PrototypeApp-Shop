package fer.shop.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
public class ShopController {

//	HibernateSessionManager sessionManager = new HibernateSessionManager();
//	private UserDao userDao;
//	@Autowired
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
//	
//	@RequestMapping(value = "/test1", method = GET)
//	public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//		return new User("Fernando","Pessina","pessi14","fer.pessina1@gmail.com");
//	}
//	
//	@RequestMapping(value = "/test2", method = GET)
//	public String testMethod() {
//		User user = new User("fernando", "pessina", "pessi14", "fer.pessina1@gmail.com");
//		System.out.println("trying to create user");
//		try{
//			HibernateSessionManager.beginTransaction();
//			userDao.save(user);
//			HibernateSessionManager.commitTransaction();
//			return "Success";
//		}catch(HibernateException ex){
//			System.out.println("Error in create User");
//            HibernateSessionManager.rollbackTransaction();
//			return "fail";
//		}
//	}


}
