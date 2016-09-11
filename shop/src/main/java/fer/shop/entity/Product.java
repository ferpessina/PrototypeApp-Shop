package fer.shop.entity;

import java.math.BigInteger;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = "PRODUCT_ID")})
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private Long productId;
	@Column(name = "PRODUCT_NAME", nullable = false)
	private String productName;
	@Column(name = "PRODUCT_DESC", nullable = false)
	private String productDescription;	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
	private Set<Category> productCategories = new HashSet<>();	
	@Column(name = "PRODUCT_PRICE", nullable = false)
	private BigInteger productPrice;	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OWNER", nullable = false)
	private User owner;
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "reservedProduct", cascade = CascadeType.ALL)	
	private Reservation reservation;
	@Column(name = "PRODUCT_LIST_DATE", nullable = false)
	private Date listDate;
	@Column(name = "PRODUCT_STATE", nullable = false)
	private String productState;
	@Column(name = "PRODUCT_IMAGE")
	private String productImage;	
	public Product(){}
	
	public Product(String name, String description, BigInteger price, String state, User owner, String image){
		this.productName = name;
		this.productDescription = description;
		this.productPrice = price;
		this.productState = state;
		this.productImage = image;
		this.owner = owner;
	}
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public BigInteger getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigInteger productPrice) {
		this.productPrice = productPrice;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Set<Category> getProductCategories() {
		return productCategories;
	}
	public void setProductCategories(Set<Category> productCategories) {
		this.productCategories = productCategories;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Date getListDate() {
		return listDate;
	}
	public void setListDate(Date listDate) {
		this.listDate = listDate;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

}
