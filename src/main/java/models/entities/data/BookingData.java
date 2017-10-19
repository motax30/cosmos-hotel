package models.entities.data;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.collections.ArrayList4;
import com.db4o.query.Predicate;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Booking;
import models.enumerates.Scope;
import models.enumerates.StatusBooking;
import models.util.GenericOperationsBdImpl;
import settings.DatabaseServer;

@Data
@AllArgsConstructor
public class BookingData extends GenericOperationsBdImpl implements Datable<Booking, Booking, String>{
	
	public BookingData(String escope) {
		openBd(escope);
	}

	@Override
	public boolean create(Booking booking,String escope) {
		openBd(escope);
		if (exists(booking.getAccommodation().getId())) {
			return false;
		}
		booking.setStatus(StatusBooking.RESERVED.toString());
		
		gravarBd(booking);
		closeBd();
		return true;
	}
	
	private boolean exists(String idAccommodation) {
		return findBy(idAccommodation)!=null;
	}		   
	
	@Override
	public Booking findBy(String idAccommodation) {
		List<Booking> res = bd.query(new Predicate<Booking>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Booking booking) {
				return booking.getStatus().equals(StatusBooking.RESERVED)&&
						booking.getAccommodation().isOcupied()==false&&
						booking.getAccommodation().getId().equals(idAccommodation);
			}
		});
		for (Booking booking : res) {
			return booking;
		}
		return null;
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
	public ObjectSet<Booking> findAllBy(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking update(Booking obj, String escope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Booking obj, String escope) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteAll(String escope) {
		// TODO Auto-generated method stub
		
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
