package fer.shop.manager;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.shop.dao.ProductDao;
import fer.shop.dao.UserDao;
import fer.shop.dto.UserDto;
import fer.shop.entity.User;

@Service
public class UserManager {
	@Autowired
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Autowired
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public User changeUserName(User user, String newName){
		return null;
	}
	
	public void login(Long userId){
		User user = userDao.findOne(userId);
		user.setLastLogin(new Date());
		userDao.save(user);
	}
	
	public void updateUser(UserDto userDto){
		User user = userDao.findOne(userDto.getUserId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth = new Date();
		try {
			dateOfBirth = df.parse(userDto.getBirthDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setDateOfBirth(dateOfBirth);
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		userDao.save(user);
	}

	public User createUser(User user){
		userDao.save(user);
		return user;
	}
	
	public User createUser(UserDto userDto){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth = new Date();
		try {
			dateOfBirth = df.parse(userDto.getBirthDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = new User(userDto.getFirstName(),userDto.getLastName(),userDto.getUserName(),userDto.getUserEmail(), dateOfBirth);
		Date date = new Date();
		user.setLastLogin(date);
		userDao.save(user);
		return user;
	}
	
	public User getByUserEmail(String email){
		User user = null;
		user = userDao.findByUserEmail(email);
		return user;
	}
	
	public List<User> getAllUsers(){
		List<User> allUsers = new ArrayList<User>();
		allUsers = (List<User>) userDao.findAll();
		return allUsers;
	}
	
    public void deleteUser(User user) {
    	user = userDao.findOne(user.getUserId());
    	user.getUserProducts().forEach(productDao::delete);
        userDao.delete(user);
    }
	
    public User getUserById(Long id) {
        User user = null;
        user = (User) userDao.findOne(id);
        return user;
    }

}
