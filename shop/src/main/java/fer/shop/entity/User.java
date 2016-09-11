package fer.shop.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "USER_ID"),@UniqueConstraint(columnNames = "USER_EMAIL")})
public class User {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Long userId;
	@Column(name = "USER_ACCOUNT_NAME", nullable = false)
	private String userName;
	@Column(name = "USER_EMAIL", nullable = false)
	private String userEmail;
	@Column(name = "USER_FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "USER_LAST_NAME", nullable = false)
	private String lastName;
	@Column(name = "USER_DATE_BIRTH", nullable = false)
	private Date dateOfBirth;
	@Column(name = "USER_LAST_LOGIN", nullable = false)
	private Date lastLogin;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = { CascadeType.ALL })
	private Set<Product> userProducts = new HashSet<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bidder", cascade = { CascadeType.ALL })
	private Set<Reservation> userBids = new HashSet<>();
	
	public User(){}
	public User(String firstName, String lastName, String userName, String userEmail){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.userEmail = userEmail;
		this.dateOfBirth = new Date();
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Set<Product> getUserProducts() {
		return userProducts;
	}
	public void setUserProducts(Set<Product> userProducts) {
		this.userProducts = userProducts;
	}
	public Set<Reservation> getUserBids() {
		return userBids;
	}
	public void setUserBids(Set<Reservation> userBids) {
		this.userBids = userBids;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}
