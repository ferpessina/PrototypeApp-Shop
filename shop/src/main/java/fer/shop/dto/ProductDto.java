package fer.shop.dto;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import fer.shop.entity.Product;
import fer.shop.entity.Reservation;

public class ProductDto {
	private Long productId;
	private String productName;
	private String productDescription;
	private BigInteger productPrice;
	private Long ownerId;
	private String creationDate;
	private String productState;
	private String productImage;
	private Long bidderId;
	
	public ProductDto(){};
	public ProductDto(Product p){
		this.productId = p.getProductId();
		this.productName = p.getProductName();
		this.productDescription = p.getProductDescription();
		this.productPrice = p.getProductPrice();
		this.ownerId = p.getOwner().getUserId();
		this.creationDate = formatDate(p.getListDate());
		this.productState = p.getProductState();
		this.productImage = p.getProductImage();
		Reservation res = p.getReservation();
		if(res != null){
			this.bidderId = p.getReservation().getBidder().getUserId();
		}
	}
	
	private String formatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String temp[] = format.format(date).split(" ",2);
		return temp[0];
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
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
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
	public Long getBidderId() {
		return bidderId;
	}
	public void setBidderId(Long bidderId) {
		this.bidderId = bidderId;
	}
}
