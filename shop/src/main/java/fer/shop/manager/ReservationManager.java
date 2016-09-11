package fer.shop.manager;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.shop.dao.ProductDao;
import fer.shop.dao.ReservationDao;
import fer.shop.dao.UserDao;
import fer.shop.entity.Product;
import fer.shop.entity.Reservation;
import fer.shop.entity.User;


@Service
public class ReservationManager {
	@Autowired
	private ReservationDao reservationDao;
	public void setUserDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	@Autowired
	private UserDao userDao;
	public void setUserManager(UserDao userDao){
		this.userDao = userDao;
	}
	@Autowired
	private ProductDao productDao;
	public void setProductManager(ProductDao productDao){
		this.productDao = productDao;
	}
	
	public void reserveProduct(Long productId, Long userId){
		Reservation res = new Reservation();
		res.setBidder(userDao.findOne(userId));
		res.setReservedProduct(productDao.findOne(productId));
		res.setReservedOn(new Date());
		reservationDao.save(res);
	}
	
	public void cancelReservation(Long productId){
		Reservation res = reservationDao.findByReservedProduct(productDao.findOne(productId));
		User bidder = userDao.findOne(res.getBidder().getUserId());
		bidder.getUserBids().remove(res);
		userDao.save(bidder);
		Product prod = productDao.findOne(productId);
		prod.setReservation(null);
		productDao.save(prod);
		reservationDao.delete(res);
	}
	
	public List<Reservation> getAllReservations(){
		return (List<Reservation>) reservationDao.findAll();
	}
}
