package fer.shop.dto;

import static org.junit.Assert.*;
import java.util.Date;

import org.junit.Test;

import fer.shop.entity.User;

public class UserDtoTest {

	UserDto userDto;
	Date now;
	Date fortyDaysAgo;

	@Test
	public void testDeletableCalculation() {
		now = new Date();
		fortyDaysAgo = new Date();
		long diff = 40;
		diff *= 24;
		diff *= 60;
		diff *= 60;
		diff *= 1000;
		fortyDaysAgo.setTime(now.getTime()-diff);
		User user = new User("Fernando", "Pessina", "pessi14", "fer.pessina@gmail.com", new Date());
		user.setLastLogin(now);
		userDto = new UserDto(user);
		assertFalse(userDto.getCanBeDeleted());
		user.setLastLogin(fortyDaysAgo);
		userDto = new UserDto(user);
		assertTrue(userDto.getCanBeDeleted());	
	}
}
