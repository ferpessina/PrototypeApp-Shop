package fer.shop.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
//import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fer.shop.dto.UserDto;
import fer.shop.entity.User;
import fer.shop.manager.UserManager;


@RestController
public class UserController {

	@Autowired
	private UserManager userManager;
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@RequestMapping(value = "/api/users/delete", method = POST)
	public String delete(@RequestParam("userid") Long id) {
		System.out.println("Deleting "+id);
		User user = userManager.getUserById(id);
		if(user != null){
			System.out.println("User retrieved");
			userManager.deleteUser(user);
		}else{
			System.out.println("user not found");
		}
		return "User Deleted";
	}

	@RequestMapping(value = "/api/users", produces = "application/json", method = GET)
	public List<UserDto> getUsers() {
		List<User> users = userManager.getAllUsers();
		List<UserDto> usersResponse = new ArrayList<>();
		if(users.size()>0){
			users.forEach(user -> usersResponse.add(new UserDto(user)));
		}else{
			System.out.println("No users in database");
		}
		return usersResponse;
	}
	
	@RequestMapping(value = "/api/users/{userId}", produces = "application/json", method = GET)
	public UserDto getUser(@PathVariable("userId") Long id) {
		System.out.println("Recieved get user "+id);
		User user = userManager.getUserById(id);
		UserDto userResponse = null;
		if(user != null){
			userResponse = new UserDto(user);
		}else{
			System.out.println("No users in database");
		}
		return userResponse;
	}
	
	@RequestMapping(value = "/api/users/{userId}/login", method = POST)
	public String userLogin(@PathVariable("userId") Long userId){
		userManager.login(userId);
		return "User login timer updated";
	}
	
	@RequestMapping(value = "/api/users/update", method = POST, consumes = "application/json")
	public String updateUser(@RequestBody UserDto newUser) {
		System.out.println(newUser.getUserId());
		System.out.println(newUser.getUserName());
		System.out.println(newUser.getFirstName());
		System.out.println(newUser.getLastName());
		System.out.println(newUser.getUserEmail());
		System.out.println(newUser.getBirthDate());
		
		if(newUser.getUserId() == null || newUser.getUserName().length()<1 || newUser.getUserEmail().length()<1 || newUser.getFirstName().length()<1 || newUser.getLastName().length()<1){
			return "Please complete all fields";
		}
		// TODO This should be a proper response object with better formatted
		// output / information similar to the loan request in the future
		String response = "User updated successfully!";
		userManager.updateUser(newUser);
		return response;
	}
	

	@RequestMapping(value = "/api/users/create", method = POST, consumes = "application/json")
	public String createUser(@RequestBody UserDto newUser) {

		System.out.println(newUser.getUserName());
		System.out.println(newUser.getFirstName());
		System.out.println(newUser.getLastName());
		System.out.println(newUser.getUserEmail());
		System.out.println(newUser.getBirthDate());
		
		if(newUser.getUserName().length()<1 || newUser.getUserEmail().length()<1 || newUser.getFirstName().length()<1 || newUser.getLastName().length()<1){
			return "Please complete all fields";
		}
		// TODO This should be a proper response object with better formatted
		// output / information similar to the loan request in the future
		String response = "User created successfully!";
		userManager.createUser(newUser);
		return response;
	}


}
