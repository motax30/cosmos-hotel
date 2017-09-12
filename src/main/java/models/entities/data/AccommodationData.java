package models.entities.data;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Accommodation;

@Data
@AllArgsConstructor
public class AccommodationData {
	
	ObjectContainer accommodationData;
	/*
	 * Constructors	
	 */
	@SuppressWarnings("deprecation")
	public AccommodationData() {
		accommodationData = Db4o.openFile("main.odb");
	}
	
	/**
	 * @return the acomodationData
	 */
	public ObjectContainer acomodationData() {
		return accommodationData;
	}
	
	/**
	 * @param acomodationData the acomodationData to set
	 */
	public void setCustomerData(ObjectContainer acomodationData) {
		this.accommodationData = acomodationData;
	}
	
	public boolean accommodationAdd(Accommodation acomodation) {
		if(!acomodationExists(acomodation)){
			if(acomodation.getAccommodationTypeInformations()!=null) {
				accommodationData.store(acomodation.getAccommodationTypeInformations());
			}
			accommodationData.store(acomodation);
			accommodationData.commit();
			return true;
		}
		return false;
	}
	
	public boolean accommodationRemove(Accommodation accommodation) {
		if(acomodationExists(accommodation)) {
			accommodationData.delete(accommodation);
			accommodationData.commit();
			return true;
		}
		return false;
	}

	private boolean acomodationExists(Accommodation accommodation) {
		return getAccommodationByType(accommodation.getTypeAccommodation())!=null;
	}

	public Accommodation getAccommodationById(String id) {
		Query query = accommodationData.query();
		query.constrain(Accommodation.class);
		query.descend("id").constrain(id).equal();
		ObjectSet<Accommodation> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	public Accommodation getAccommodationByType(String type) {
		Query query = accommodationData.query();
		query.constrain(Accommodation.class);
		query.descend("typeAccommodation").constrain(type).equal();
		ObjectSet<Accommodation> result = query.execute();
		return result.hasNext()?result.next() : null;
	}
	
	public Accommodation accommodationUpdate(Accommodation accommodation) {
		Accommodation currentAccommodation = getAccommodationById(accommodation.getId());
		currentAccommodation.setTypeAccommodation(accommodation.getTypeAccommodation());
		currentAccommodation.setAccommodationTypeInformations(accommodation.getAccommodationTypeInformations());
		accommodationData.store(currentAccommodation);
		accommodationData.commit();
		return currentAccommodation;
	}
	
	public ObjectSet<Accommodation> getAccommodations(){
		Query query = accommodationData.query();
		query.constrain(Accommodation.class);
		return query.execute();
	}
}