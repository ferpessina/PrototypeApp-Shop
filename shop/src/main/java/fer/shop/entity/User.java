package fer.shop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "USER_ID"),@UniqueConstraint(columnNames = "USER_EMAIL")})
public class User {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Long userId;
	@Column(name = "USER_FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "USER_LAST_NAME", nullable = false)
	private String lastName;
	@Column(name = "USER_ACCOUNT_NAME", nullable = false)
	private String userName;
	@Column(name = "USER_EMAIL", nullable = false)
	private String userEmail;
	@Column(name = "USER_DATE_BIRTH", nullable = false)
	private Date dateOfBirth;
	
	public User(){}
	public User(String firstName, String lastName, String userName, String userEmail){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.userEmail = userEmail;
		this.dateOfBirth = new Date();
	}
	
	public Long getUserId(){
		return userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
