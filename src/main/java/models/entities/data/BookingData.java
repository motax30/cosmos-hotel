package models.entities.data;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import models.entities.Accommodation;
import models.entities.Booking;
import models.entities.Booking;
import models.enumerates.StatusBooking;
import models.util.GenericOperationsBdImpl;

@AllArgsConstructor
public class BookingData extends GenericOperationsBdImpl implements Datable<Booking, Booking, String>{
	
	public BookingData(String escope) {
		openBd(escope);
	}
	@Override
	public void isBdNullOrClosed(String escope) {
		if(bd==null||bd.ext().isClosed()) {
			openBd(escope);
		}
	}

	@Override
	public boolean create(Booking booking,String escope) {
		isBdNullOrClosed(escope);
		if (exists(booking.getAccommodation().getId(),escope)) {
			return false;
		}
		booking.setStatus(StatusBooking.RESERVED.toString());
		
		gravarBd(booking);
		closeBd();
		return true;
	}	   
	
	@Override
	public Booking update(Booking booking, String escope) {
		isBdNullOrClosed(escope);
		Booking currentBooking = findBy("id", booking.getId());
		LocalDateTime createdAt = currentBooking.getCreatedAt();

		BeanUtils.copyProperties(booking, currentBooking);
		currentBooking.setUpdatedAt(LocalDateTime.now());
		currentBooking.setCreatedAt(createdAt);

		gravarBd((Booking)currentBooking);
		closeBd();
		return currentBooking;
	}

	@Override
	public boolean delete(Booking booking, String escope) {
		isBdNullOrClosed(escope);
		try {
			delEntityToBd(booking);
			closeBd();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@Override
	public void deleteAll(String escope) {
		isBdNullOrClosed(escope);
		for(Booking booking : findAll(escope)) {
			delEntityToBd(booking);
		}
		closeBd();
	}

	@Override
	public boolean exists(String bookId, String escope) {
		return findBy(bookId,escope) != null;
	}
	
	@Override
	public Booking findBy(String idAccommodation,String escope) {
		isBdNullOrClosed(escope);
		List<Booking> res = bd.query(new Predicate<Booking>() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unlikely-arg-type")
			@Override
			public boolean match(Booking booking) {
				return booking.getStatus().equals(StatusBooking.RESERVED)&&
						booking.getAccommodation().isOcupied()==false&&
						booking.getAccommodation().getId().equals(idAccommodation);
			}
		});
		closeBd();
		for (Booking booking : res) {
			return booking;
		}
		return null;
	}
	
	@Override
	public boolean exists(String key, String value, String escope) {
		return findBy(key, value,escope) != null;
	}
	
	@Override
	public Booking findBy(String key, String value, String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Booking.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Booking> result = query.execute();
		closeBd();
		return result.hasNext() ? result.next() : null;
	}
	@Override
	public ObjectSet<Booking> findAll(String escope) {
		isBdNullOrClosed(escope);
		return bd.queryByExample(Booking.class);
	}

	@Override
	public ObjectSet<Booking> findAllBy(String key, String value, String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Booking.class);
		query.descend(key).constrain(value);
		ObjectSet<Booking> res = query.execute();
		closeBd();
		return res;
	}

	@Override
	public ObjectSet<Booking> findAllBy(String value, String escope) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

	
//	public boolean create(Booking booking) {
//		boolean criado = false;
//		try {
//			if(!bookingExists(booking)) {
//				bookingData.store(booking);
//				bookingData.commit();
//				criado = true;
//			}
//		} catch (AssertionError e) {
//			e.getMessage();
//		}
//		return criado;
//	}
//
//	private boolean bookingExists(Booking booking) {
//		return this.getBookingByAccommodation(booking)!=null;
//	}
//
//	private Booking getBookingByAccommodation(Booking booking) {
//		Query query1 = bookingData.query();
//		query1.constrain(Booking.class);
//		query1.descend("accommodation").constrain(booking.getAccommodation());
//		query1.descend("id").constrain(booking.getAccommodation().getId());
//		ObjectSet<Booking> result = query1.execute();
//		return result.hasNext()?result.next():null;
//	}
}
