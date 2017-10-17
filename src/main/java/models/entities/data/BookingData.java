package models.entities.data;
import java.util.ListIterator;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Accommodation;
import models.entities.Booking;
import settings.DatabaseServer;

@Data
@AllArgsConstructor
public class BookingData implements Datable<Booking, Booking, String>{
	private ObjectContainer bookingData;
	private Class DelegatingActivatableCollection;
	
	public BookingData() {
		bookingData = DatabaseServer.getServer().openClient();
	}

	@Override
	public boolean create(Booking booking) {
		if (exists(booking.getAccommodation().getId())) {
			return false;
		}
		if(!booking.getAccommodation().isOcupied()) {
			bookingData.store(booking);
			closeConnection(bookingData);
			return true;
		}
		return false;
	}

	@Override
	public Booking update(Booking booking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Booking booking) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean exists(String idAccommodation) {
		return findAccommodationInBooking(idAccommodation)!=null;
	}
	
	private Booking findAccommodationInBooking(String idAccommodation) {
		return findInBooking(idAccommodation);
	}
	
	private Booking findInBooking(Object obj) {
		Booking encontrado = null;
		Booking b = null;
		ObjectSet<Object> query = bookingData.queryByExample(Booking.class);
		while (query.hasNext()) {
			b = (Booking) query.next();
			if (b.getAccommodation().getId().equals(obj)) {
				encontrado = b;
			}
		}
		return encontrado;
	}
	
	@Override
	public boolean exists(String key, String value) {return false;}
	@Override
	public Booking findBy(String key, String value) {return null;}

	@Override
	public ObjectSet<Booking> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectSet<Booking> findAllBy(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean closeConnection(ObjectContainer conexao) {
		boolean closed = false;
		if (bookingData.close()) {
			closed= true;
		};
		return closed;
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
