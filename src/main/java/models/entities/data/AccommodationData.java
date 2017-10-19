package models.entities.data;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.NoArgsConstructor;
import models.entities.Accommodation;
import models.util.GenericOperationsBdImpl;

public class AccommodationData extends GenericOperationsBdImpl implements Datable<Accommodation,Accommodation,String> {
	
	public AccommodationData(String escope) {
		openBd(escope);
	}
	
	@Override
	public boolean create(Accommodation acc,String escope) {
		openBd(escope);
		if(exists("id", acc.getId())&&!(acc.getAccommodationTypeInformations().getTypeAccommodation().isEmpty())) {
			return false;
		}
		acc.setCreatedAt(LocalDateTime.now());
		acc.setUpdatedAt(LocalDateTime.now());
		if(acc.getAccommodationTypeInformations().getDailyPrice()!=0 && acc.getAccommodationTypeInformations().getNumberBeds()!=0) {
			bd.store(acc.getAccommodationTypeInformations());
		}
		gravarBd((Accommodation)acc);
		closeBd();
		return true;
	}
	

	@Override
	public Accommodation update(Accommodation acc,String escope) {
		openBd(escope);
		Accommodation currentAccommodation = findBy("id", acc.getId());
		LocalDateTime createdAt = currentAccommodation.getCreatedAt();

		BeanUtils.copyProperties(acc, currentAccommodation);
		currentAccommodation.setUpdatedAt(LocalDateTime.now());
		currentAccommodation.setCreatedAt(createdAt);

		gravarBd((Accommodation)currentAccommodation);
		closeBd();
		return currentAccommodation;
	}

	@Override
	public boolean delete(Accommodation acc,String escope) {
		try {
			openBd(escope);
			deletarEntidadeBd(acc);
			closeBd();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@Override
	public void deleteAll(String escope) {
		openBd(escope);
		for(Accommodation accommodation : findAll()) {
			deletarEntidadeBd(accommodation);
			closeBd();
		}
	}

	@Override
	public boolean exists(String key, String value) {
		return findBy(key, value) != null;
	}

	@Override
	public Accommodation findBy(String key, String value) {
		Query query = bd.query();
		query.constrain(Accommodation.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Accommodation> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}

	@Override
	public ObjectSet<Accommodation> findAll() {
		Query query = bd.query();
		query.constrain(Accommodation.class);
		return query.execute();
	}

	@Override
	public ObjectSet<Accommodation> findAllBy(String key, String value) {
		Query query = bd.query();
		query.constrain(Accommodation.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}

	@Override
	public Accommodation findBy(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectSet<Accommodation> findAllBy(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}