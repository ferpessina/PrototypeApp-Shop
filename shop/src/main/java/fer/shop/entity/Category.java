package fer.shop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.*;

@Entity
public class Category {
	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	private Long catId;
	
	@Column(name = "CATEGORY_NAME", nullable = false)
	private String catName;
	
	@Column(name = "CATEGORY_DESC", nullable = false)
	private String catDescription;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "CATEGORY_PRODUCTS", joinColumns = {
			@JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false) }, inverseJoinColumns = { 
			@JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false) })
	private Set<Product> products = new HashSet<>();
	
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDescription() {
		return catDescription;
	}
	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
