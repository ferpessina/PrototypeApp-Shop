package fer.shop.dto;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import fer.shop.entity.Product;
import fer.shop.entity.Reservation;
import fer.shop.entity.User;

import static org.mockito.Mockito.mock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReservationDtoTest {

	@Test
	public void testFormatDate() throws Exception {
		Reservation r = mock(Reservation.class);
		User u = mock(User.class);
		Product p = mock(Product.class);
		String string = "January 2, 2010";
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date = format.parse(string);
		System.out.println(date);
        when(r.getReservedOn()).thenReturn(date);
        when(r.getReservedProduct()).thenReturn(p);
        when(r.getBidder()).thenReturn(u);
        when(p.getProductId()).thenReturn((long) 10);
        when(u.getUserId()).thenReturn((long) 10);
        ReservationDto testObj = new ReservationDto(r);
        System.out.println(testObj.getReservedOn());
        assertTrue(testObj.getReservedOn().equals("01/02/2010"));
	}

}
