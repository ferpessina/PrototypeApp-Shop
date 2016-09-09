package fer.shop.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.shop.resource.HibernateSessionManager;
import fer.shop.dao.UserDao;
import fer.shop.entity.User;

@Service
public class UserManager {
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User changeUserName(User user, String newName){
		try{
			HibernateSessionManager.beginTransaction();
			user = userDao.findByUserEmail(user.getUserEmail());
			if(user == null){
				System.out.println("User Not Found");
			}else{
				user.setUserName(newName);
				userDao.update(user);
			}
			HibernateSessionManager.commitTransaction();
			return user;
		}catch(HibernateException ex){
			System.out.println("Error in userChangeName");
            HibernateSessionManager.rollbackTransaction();
			return null;
		}
	}
	
	public User changeUserEmail(User user, String newEmail){
		try{
			HibernateSessionManager.beginTransaction();
			user = userDao.findByUserEmail(user.getUserEmail());
			if(user == null){
				System.out.println("User Not Found");
			}else{
				User user2 = userDao.findByUserEmail(newEmail);
				if(user2 == null){
					user.setUserEmail(newEmail);
					userDao.update(user);
				}else{
					System.out.println("New email already exists");
				}
			}
			HibernateSessionManager.commitTransaction();
			return user;
		}catch(HibernateException ex){
			System.out.println("Error in userChangeEmail");
            HibernateSessionManager.rollbackTransaction();
			return null;
		}
	}

	public User createUser(String firstName, String lastName, String userName, String email){
		try{
			HibernateSessionManager.beginTransaction();
			User user = new User();
			user = userDao.findByUserEmail(email);
			if(user == null){
				System.out.println("Creating new user");
				user = new User(firstName, lastName, userName, email);
				userDao.save(user);
			}
			HibernateSessionManager.commitTransaction();
			return user;
		}catch(HibernateException ex){
			System.out.println("Error in create User");
            HibernateSessionManager.rollbackTransaction();
			return null;
		}
	}
	
	public User findByUserEmail(String email){
		User user = null;
		try{
			HibernateSessionManager.beginTransaction();
			user = userDao.findByUserEmail(email);
			HibernateSessionManager.commitTransaction();
		}catch(HibernateException ex){
			System.out.println("Error in findByUserEmail");
			System.out.println(ex);
		}
		return user;
	}
	
	public List<User> findAllUsers(){
		List<User> allUsers = new ArrayList<User>();
		try{
			HibernateSessionManager.beginTransaction();
			allUsers = userDao.findAll(User.class);
			HibernateSessionManager.commitTransaction();
		}catch(HibernateException ex){
			System.out.println("Error in findAllUsers");
		}
		return allUsers;
	}
	
    public void deleteUser(User user) {
        try {
        	HibernateSessionManager.beginTransaction();
        	user = userDao.findByID(User.class, user.getUserId());
        	userDao.delete(user);
            HibernateSessionManager.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error deleting user");
            HibernateSessionManager.rollbackTransaction();
        }
    }
	
    public User findUserById(Long id) {
        User user = null;
        try {
        	HibernateSessionManager.beginTransaction();
            user = (User) userDao.findByID(User.class, id);
            HibernateSessionManager.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error finding user by ID");
        }
        return user;
    }

}
