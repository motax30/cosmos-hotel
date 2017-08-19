package models;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import settings.DatabaseServer;


public class UserData {
	ObjectContainer usersData;
	
	/*
	 * Constructors	
	 */
	public UserData() {
		usersData = DatabaseServer.getServer().openClient();
	}
	
	public UserData(ObjectContainer usersData) {
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
	public boolean userAdd(User user) {
		if (!userExists(user.getUserName())) {
			usersData.store(user);
			usersData.commit();
			return true;
		}
		return false;
	}
	
	public boolean userExists(String userName) {
		return this.getUserByUserName(userName) == null ? false : true;
	}
	
	public User getUserByUserName(String userName) {
		Query query = usersData.query();
		query.constrain(User.class);
		query.descend("userName").constrain(userName).equal();
		ObjectSet<User> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	public boolean userLogin(String userName, String password) {
		Query query = usersData.query();
		Constraint constrain = query.descend("userName").constrain(userName);
		query.descend("password").constrain(password).and(constrain);
		
		ObjectSet<User> result = query.execute();
		return result.hasNext();
	}
	
	public ObjectSet<User> getUsers() {
		Query query = usersData.query();
		query.constrain(User.class);
		return query.execute();
	}
}
