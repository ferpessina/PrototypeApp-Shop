package fer.shop.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import fer.shop.entity.Product;
import fer.shop.entity.Reservation;

@Transactional
public interface ReservationDao extends CrudRepository<Reservation,Long>{
	public Reservation findByReservedProduct(Product reservedProduct);
}
