package models.entities.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import models.entities.Receptionist;
import models.entities.Receptionist;
import models.util.GenericOperationsBdImpl;

@AllArgsConstructor
public class ReceptionistData extends GenericOperationsBdImpl implements Datable<Receptionist, Receptionist,String>{

	public ReceptionistData(String escope) {
		openBd(escope);
	}
	
	@Override
	public void isBdNullOrClosed(String escope) {
		if(bd==null||bd.ext().isClosed()) {
			openBd(escope);
		}
	}
	
	public boolean receptionistLogin(String userName, String password,String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Receptionist.class);
		Constraint constrain = query.descend("userName").constrain(userName);
		query.descend("password").constrain(password).and(constrain);
		
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext();
	}

	@Override
	public boolean create(Receptionist recep,String escope) {
		isBdNullOrClosed(escope);
		if (exists("userName", recep.getUserName())||exists("password",recep.getPassword())) {
			return false;
		}
		
		recep.setCreatedAt(LocalDateTime.now());
		recep.setUpdatedAt(LocalDateTime.now());
		gravarBd(recep);
		closeBd();
		return true;
	}

	@Override
	public Receptionist update(Receptionist recep, String escope) {
		isBdNullOrClosed(escope);
		Receptionist currentRecepcionist = findBy("id", recep.getId());
		LocalDateTime createdAt = currentRecepcionist.getCreatedAt();

		BeanUtils.copyProperties(recep, currentRecepcionist);
		currentRecepcionist.setUpdatedAt(LocalDateTime.now());
		currentRecepcionist.setCreatedAt(createdAt);

		gravarBd(currentRecepcionist);
		closeBd();

		return currentRecepcionist;
	}

	@Override
	public boolean delete(Receptionist rcp, String escope) {
		isBdNullOrClosed(escope);
		try {
			delEntityToBd(rcp);
			closeBd();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@Override
	public void deleteAll(String escope) {
		isBdNullOrClosed(escope);
		for(Receptionist reception : findAll(escope)) {
			delEntityToBd(reception);
		}
		closeBd();
	}

	@Override
	public boolean exists(String recepId, String escope) {
		return findBy(recepId, escope) != null;
	}
	
	@Override
	public Receptionist findBy(String recepId, String escope) {
		isBdNullOrClosed(escope);
		List<Receptionist> res = bd.query(new Predicate<Receptionist>() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unlikely-arg-type")
			@Override
			public boolean match(Receptionist recepcionist) {
				return recepcionist.getId().equals(recepId);
			}
		});
		for (Receptionist recepcionist : res) {
			return recepcionist;
		}
		return null;
	}
	
	@Override
	public boolean exists(String key, String value, String escope) {
		return findBy(key, value,escope) != null;
	}
	
	@Override
	public Receptionist findBy(String key, String value,String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Receptionist.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Receptionist> result = query.execute();
		closeBd();
		return result.hasNext() ? result.next() : null;
	}

	@Override
	public ObjectSet<Receptionist> findAll(String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Receptionist.class);
		ObjectSet<Receptionist> res = query.execute();
		closeBd();
		return res;
	}

	@Override
	public ObjectSet<Receptionist> findAllBy(String key, String value,String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Receptionist.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Receptionist> res = query.execute();
		return res;
	}

	@Override
	public ObjectSet<Receptionist> findAllBy(String value, String escope) {
		return null;
	}

	
}