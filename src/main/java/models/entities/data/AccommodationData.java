package models.entities.data;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Accommodation;

@Data
@AllArgsConstructor
public class AccommodationData implements Datable<Accommodation,Accommodation,String> {
	
	private ObjectContainer accommodationData;
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

	@Override
	public boolean create(Accommodation acc) {
		if(exists("id", acc.getId())&&!(acc.getTypeAccommodation().isEmpty())) {
			return false;
		}
		acc.setCreatedAt(LocalDateTime.now());
		acc.setUpdatedAt(LocalDateTime.now());
		if(acc.getAccommodationTypeInformations().getDailyPrice()!=0 && acc.getAccommodationTypeInformations().getNumberBeds()!=0) {
			accommodationData.store(acc.getAccommodationTypeInformations());
		}
		accommodationData.store(acc);
		accommodationData.commit();
		return true;
	}
	

	@Override
	public Accommodation update(Accommodation acc) {
		Accommodation currentAccommodation = findBy("id", acc.getId());
		LocalDateTime createdAt = currentAccommodation.getCreatedAt();

		BeanUtils.copyProperties(acc, currentAccommodation);
		currentAccommodation.setUpdatedAt(LocalDateTime.now());
		currentAccommodation.setCreatedAt(createdAt);

		accommodationData.store(currentAccommodation);
		accommodationData.commit();

		return currentAccommodation;
	}

	@Override
	public boolean delete(Accommodation acc) {
		try {
			accommodationData.delete(acc);
			accommodationData.commit();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@Override
	public void deleteAll() {
		for(Accommodation accommodation : findAll()) {
			accommodationData.delete(accommodation);
			accommodationData.commit();
		}
	}

	@Override
	public boolean exists(String key, String value) {
		return findBy(key, value) != null;
	}

	@Override
	public Accommodation findBy(String key, String value) {
		Query query = accommodationData.query();
		query.constrain(Accommodation.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Accommodation> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}

	@Override
	public ObjectSet<Accommodation> findAll() {
		Query query = accommodationData.query();
		query.constrain(Accommodation.class);
		return query.execute();
	}

	@Override
	public ObjectSet<Accommodation> findAllBy(String key, String value) {
		Query query = accommodationData.query();
		query.constrain(Accommodation.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}

	@Override
	public boolean closeConnection(ObjectContainer conexao) {
		boolean closed = false;
		if (accommodationData.close()) {
			closed= true;
		};
		return closed;
	}
}