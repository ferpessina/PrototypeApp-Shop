package fer.shop.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.shop.dto.ReservationDto;
import fer.shop.entity.Reservation;
import fer.shop.manager.ReservationManager;

@RestController
public class ReservationController {
	@Autowired
	private ReservationManager reservationManager;
	public void setUserManager(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
	}
	
	@RequestMapping(value = "/api/products/{productId}/reserve", method = POST, consumes = "application/json")
	public String reserveProduct(@PathVariable("productId") Long productId, @RequestBody Long bidderId){
		reservationManager.reserveProduct(productId, bidderId);
		return "Product reserved.";
	}
	@RequestMapping(value = "/api/products/{productId}/rescancel", method = POST)
	public String cancelReservation(@PathVariable("productId") Long productId){
		reservationManager.cancelReservation(productId);
		return "Reservation canceled.";
	}
	
	@RequestMapping(value = "/api/products/reservations", produces = "application/json", method = GET)
	public List<ReservationDto> getReservations() {
		List<Reservation> reservations = reservationManager.getAllReservations();
		List<ReservationDto> res = new ArrayList<>();
		if(reservations.size()>0){
			reservations.forEach(r -> res.add(new ReservationDto(r)));
		}else{
			System.out.println("No reservations");
		}
		return res;
	}
	
	@RequestMapping(value = "/api/products/reservations/cleanup", method = POST)
	public void cleanupReservations() {
		List<Reservation> reservations = reservationManager.getAllReservations();
		for(int i=0;i<reservations.size();i++){
			if(reservations.get(i).checkIfOutdated()){
				reservationManager.cancelReservation(reservations.get(i).getReservationId());
			}
		}
	}
	
}
