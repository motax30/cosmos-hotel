package models.entities.data;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Receptionist;
import models.entities.Receptionist;
import models.entities.Receptionist;
import settings.DatabaseServer;

@Data
@AllArgsConstructor
public class ReceptionistData implements Datable<Receptionist, Receptionist,String>{
	private ObjectContainer receptionistData;

	public ReceptionistData() {
		receptionistData = DatabaseServer.getServer().openClient();
	}
	
	public boolean receptionistLogin(String userName, String password) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		Constraint constrain = query.descend("userName").constrain(userName);
		query.descend("password").constrain(password).and(constrain);
		
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext();
	}

	@Override
	public boolean create(Receptionist recep) {
		if (exists("userName", recep.getUserName())||exists("password",recep.getPassword())) {
			return false;
		}
		
		recep.setCreatedAt(LocalDateTime.now());
		recep.setUpdatedAt(LocalDateTime.now());
		receptionistData.store(recep);
		receptionistData.commit();
		return true;
	}

	@Override
	public Receptionist update(Receptionist recep) {
		Receptionist currentRecepcionist = findBy("id", recep.getId());
		LocalDateTime createdAt = currentRecepcionist.getCreatedAt();

		BeanUtils.copyProperties(recep, currentRecepcionist);
		currentRecepcionist.setUpdatedAt(LocalDateTime.now());
		currentRecepcionist.setCreatedAt(createdAt);

		receptionistData.store(currentRecepcionist);
		receptionistData.commit();

		return currentRecepcionist;
	}

	@Override
	public boolean delete(Receptionist rcp) {
		try {
			receptionistData.delete(rcp);
			receptionistData.commit();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@Override
	public void deleteAll() {
		for(Receptionist reception : findAll()) {
			receptionistData.delete(reception);
			receptionistData.commit();
		}
	}

	@Override
	public boolean exists(String key, String value) {
		return findBy(key, value) != null;
	}

	@Override
	public Receptionist findBy(String key, String value) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}

	@Override
	public ObjectSet<Receptionist> findAll() {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		return query.execute();
	}

	@Override
	public ObjectSet<Receptionist> findAllBy(String key, String value) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}

	@Override
	public boolean closeConnection(ObjectContainer conexao) {
		boolean closed = false;
		if (receptionistData.close()) {
			closed= true;
		};
		return closed;
	}
}