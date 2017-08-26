package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import models.entities.Receptionist;
import settings.DatabaseServer;

public class ReceptionistData {
	ObjectContainer usersData;
	
	/*
	 * Constructors	
	 */
	public ReceptionistData() {
		usersData = DatabaseServer.getServer().openClient();
	}
	
	public ReceptionistData(ObjectContainer usersData) {
		super();
		this.usersData = usersData;
	}
	
	/*
	 * Get and Setters
	 */
	public ObjectContainer getUsersData() {
		return usersData;
	}
	public void setUsersData(ObjectContainer usersData) {
		this.usersData = usersData;
	}
	
	/*
	 * Public Methods
	 */
	public boolean receptionistAdd(Receptionist user) {
		if (!receptionistExists(user.getUserName())) {
			usersData.store(user);
			usersData.commit();
			return true;
		}
		return false;
	}
	
	public boolean receptionistExists(String userName) {
		return this.getReceptionistByUserName(userName) == null ? false : true;
	}
	
	public Receptionist getReceptionistByUserName(String userName) {
		Query query = usersData.query();
		query.constrain(Receptionist.class);
		query.descend("userName").constrain(userName).equal();
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	public boolean receptionistLogin(String userName, String password) {
		Query query = usersData.query();
		query.constrain(Receptionist.class);
		Constraint constrain = query.descend("userName").constrain(userName);
		query.descend("password").constrain(password).and(constrain);
		
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext();
	}
	
	public ObjectSet<Receptionist> getReceptionists() {
		Query query = usersData.query();
		query.constrain(Receptionist.class);
		return query.execute();
	}
}
