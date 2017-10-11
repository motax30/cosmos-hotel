package models.entities.data;
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
public class BookingData {
	private ObjectContainer bookingData;
	
	public BookingData() {
		bookingData = DatabaseServer.getServer().openClient();
	}
	
	public boolean create(Booking booking) {
		boolean criado = false;
		try {
			if(!bookingExists(booking)) {
				bookingData.store(booking);
				bookingData.commit();
				criado = true;
			}
		} catch (AssertionError e) {
			e.getMessage();
		}
		return criado;
	}

	private boolean bookingExists(Booking booking) {
		return this.getBookingByAccommodation(booking)!=null;
	}

	private Booking getBookingByAccommodation(Booking booking) {
		Query query1 = bookingData.query();
		query1.constrain(Booking.class);
		query1.descend("accommodation").constrain(booking.getAccommodation());
		query1.descend("id").constrain(booking.getAccommodation().getId());
		ObjectSet<Booking> result = query1.execute();
		return result.hasNext()?result.next():null;
	}
}
