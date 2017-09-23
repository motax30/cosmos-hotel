package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Receptionist;
import settings.DatabaseServer;

@Data
@AllArgsConstructor
public class ReceptionistData {
	private ObjectContainer receptionistData;

	public ReceptionistData() {
		receptionistData = DatabaseServer.getServer().openClient();
	}

	/*
	 * Public Methods
	 */
	public boolean receptionistAdd(Receptionist receptionist) {
		if(!receptionistExists(receptionist.getUserName())) {
			receptionistData.store(receptionist);
			receptionistData.commit();
			return true;
		}
		return false;
	}
	
	public boolean receptionistRemove(Receptionist receptionist) {
		if(receptionistExists(receptionist.getUserName())) {
			receptionistData.delete(receptionist);
			receptionistData.commit();
			return true;
		}
		return false;
	}

	public boolean receptionistExists(String userName) {
		return this.getReceptionistByReceptionistUserName(userName) != null;
	}

	public Receptionist getReceptionistById(String id) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		query.descend("id").constrain(id).equal();
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}

	public Receptionist receptionistUpdate(Receptionist receptionist) {
		Receptionist currentReceptionist = getReceptionistById(receptionist.getId());
		currentReceptionist.setName(receptionist.getName());
		currentReceptionist.setNotes(receptionist.getNotes());
		receptionistData.store(currentReceptionist);
		receptionistData.commit();
		return currentReceptionist;
	}
	
	public Receptionist getReceptionistByReceptionistUserName(String userName) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		query.descend("userName").constrain(userName).equal();
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	public boolean receptionistLogin(String userName, String password) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		Constraint constrain = query.descend("userName").constrain(userName);
		query.descend("password").constrain(password).and(constrain);
		
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext();
	}
	
	public ObjectSet<Receptionist> getReceptionists(){
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		return query.execute();
	}
}