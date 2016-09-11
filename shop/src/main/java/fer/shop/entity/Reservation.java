package fer.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue
	@Column(name = "RESERVATION_ID", unique = true, nullable = false)
	private Long reservationId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product reservedProduct;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User bidder;
	@Column(name = "RESERVED_ON", nullable = false)
	private Date reservedOn;
	
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public Product getReservedProduct() {
		return reservedProduct;
	}
	public void setReservedProduct(Product reservedProduct) {
		this.reservedProduct = reservedProduct;
	}
	public User getBidder() {
		return bidder;
	}
	public void setBidder(User bidder) {
		this.bidder = bidder;
	}
	public Date getReservedOn() {
		return reservedOn;
	}
	public void setReservedOn(Date reservedOn) {
		this.reservedOn = reservedOn;
	}
	public boolean checkIfOutdated(){
		Date now = new Date();
		long diff = now.getTime() - reservedOn.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return (diffDays > 7);
	}
}
