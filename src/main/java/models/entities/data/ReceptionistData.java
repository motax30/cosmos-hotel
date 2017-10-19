package models.entities.data;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import models.entities.Receptionist;
import models.util.GenericOperationsBdImpl;

@AllArgsConstructor
public class ReceptionistData extends GenericOperationsBdImpl implements Datable<Receptionist, Receptionist,String>{

	public ReceptionistData(String escope) {
		openBd(escope);
	}
	
	public boolean receptionistLogin(String userName, String password) {
		Query query = bd.query();
		query.constrain(Receptionist.class);
		Constraint constrain = query.descend("userName").constrain(userName);
		query.descend("password").constrain(password).and(constrain);
		
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext();
	}

	@Override
	public boolean create(Receptionist recep,String escope) {
		openBd(escope);
		if (exists("userName", recep.getUserName())||exists("password",recep.getPassword())) {
			return false;
		}
		
		recep.setCreatedAt(LocalDateTime.now());
		recep.setUpdatedAt(LocalDateTime.now());
		bd.store(recep);
		bd.commit();
		return true;
	}

	@Override
	public Receptionist update(Receptionist recep, String escope) {
		Receptionist currentRecepcionist = findBy("id", recep.getId());
		LocalDateTime createdAt = currentRecepcionist.getCreatedAt();

		BeanUtils.copyProperties(recep, currentRecepcionist);
		currentRecepcionist.setUpdatedAt(LocalDateTime.now());
		currentRecepcionist.setCreatedAt(createdAt);

		bd.store(currentRecepcionist);
		bd.commit();

		return currentRecepcionist;
	}

	@Override
	public boolean delete(Receptionist rcp, String escope) {
		try {
			bd.delete(rcp);
			bd.commit();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@Override
	public void deleteAll(String escope) {
		for(Receptionist reception : findAll(escope)) {
			bd.delete(reception);
			bd.commit();
		}
	}

	@Override
	public boolean exists(String key, String value) {
		return findBy(key, value) != null;
	}

	@Override
	public Receptionist findBy(String key, String value) {
		Query query = bd.query();
		query.constrain(Receptionist.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}

	@Override
	public ObjectSet<Receptionist> findAll(String escope) {
		Query query = bd.query();
		query.constrain(Receptionist.class);
		return query.execute();
	}

	@Override
	public ObjectSet<Receptionist> findAllBy(String key, String value) {
		Query query = bd.query();
		query.constrain(Receptionist.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}

	@Override
	public Receptionist findBy(String entity, String entity2, String escope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectSet<Receptionist> findAllBy(String key, String value, String escope) {
		// TODO Auto-generated method stub
		return null;
	}
}