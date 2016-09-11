package fer.shop.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import fer.shop.entity.Reservation;

public class ReservationDto {
	private Long productId;
	private Long userId;
	private String reservedOn;
	
	public ReservationDto(Reservation r) {
		this.productId = r.getReservedProduct().getProductId();
		this.userId = r.getBidder().getUserId();
		this.reservedOn = formatDate(r.getReservedOn());
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getReservedOn() {
		return reservedOn;
	}
	public void setReservedOn(String reservedOn) {
		this.reservedOn = reservedOn;
	}
}
