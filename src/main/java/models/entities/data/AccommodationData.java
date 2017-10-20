package models.entities.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import models.entities.Accommodation;
import models.util.GenericOperationsBdImpl;

public class AccommodationData extends GenericOperationsBdImpl implements Datable<Accommodation,Accommodation,String> {
	
	public AccommodationData(String escope) {
		openBd(escope);
	}

	@Override
	public void isBdNullOrClosed(String escope) {
		if(bd==null||bd.ext().isClosed()) {
			openBd(escope);
		}
	}
	
	@Override
	public boolean create(Accommodation acc,String escope) {
		isBdNullOrClosed(escope);
		if(exists("id", acc.getId(),escope)) {
			return false;
		}
		acc.setCreatedAt(LocalDateTime.now());
		acc.setUpdatedAt(LocalDateTime.now());
		if(acc.getAccommodationTypeInformations().getDailyPrice()!=0 && acc.getAccommodationTypeInformations().getNumberBeds()!=0) {
			gravarBd(acc);
			closeBd();
		}
		return true;
	}
	
	@Override
	public Accommodation update(Accommodation acc,String escope) {
		isBdNullOrClosed(escope);
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
		isBdNullOrClosed(escope);
		try {
			delEntityToBd(acc);
			closeBd();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@Override
	public void deleteAll(String escope) {
		isBdNullOrClosed(escope);
		for(Accommodation accommodation : findAll(escope)) {
			delEntityToBd(accommodation);
		}
		closeBd();
	}

	@Override
	public boolean exists(String acmdId, String escope) {
		return findBy(acmdId,escope) != null;
	}
	
	@Override
	public Accommodation findBy(String acmdId, String escope) {
		isBdNullOrClosed(escope);
		List<Accommodation> res = bd.query(new Predicate<Accommodation>() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unlikely-arg-type")
			@Override
			public boolean match(Accommodation accommodation) {
				return accommodation.getId().equals(acmdId);
			}
		});
		closeBd();
		for (Accommodation accommodation : res) {
			return accommodation;
		}
		return null;
	}
	
	@Override
	public boolean exists(String key, String value,String escope) {
		return findBy(key, value,escope) != null;
	}
	@Override
	public Accommodation findBy(String key, String value,String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Accommodation.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Accommodation> result = query.execute();
		closeBd();
		return result.hasNext() ? result.next() : null;
	}

	@Override
	public ObjectSet<Accommodation> findAll(String escope) {
		isBdNullOrClosed(escope);
		return bd.queryByExample(Accommodation.class);
	}

	@Override
	public ObjectSet<Accommodation> findAllBy(String key, String value, String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Accommodation.class);
		query.descend(key).constrain(value);
		ObjectSet<Accommodation> res = query.execute();
		closeBd();
		return res;
	}

	@Override
	public ObjectSet<Accommodation> findAllBy(String termSearch, String escope) {
		return null;
	}
	
}