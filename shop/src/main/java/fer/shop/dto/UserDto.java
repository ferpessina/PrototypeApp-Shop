package fer.shop.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import fer.shop.entity.User;

public class UserDto {
	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String userEmail;
	private String birthDate;
	private boolean canBeDeleted;
	
	public UserDto(){}
	
	public UserDto(User user){
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.userEmail = user.getUserEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.birthDate = formatDate(user.getDateOfBirth());
		this.canBeDeleted = assertDeletable(user.getLastLogin());
	}

	private boolean assertDeletable(Date lastLogin){
		Date now = new Date();
		System.out.println(lastLogin);
		System.out.println(now+"\n");
		long diff = now.getTime() - lastLogin.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		System.out.println(diffDays);
		return (diffDays > 30);
	}
	
	private String formatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String temp[] = format.format(date).split(" ",2);
		return temp[0];
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isCanBeDeleted() {
		return canBeDeleted;
	}

	public void setCanBeDeleted(boolean canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}
	
}
