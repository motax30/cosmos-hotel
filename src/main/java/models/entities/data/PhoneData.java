package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import models.entities.Customer;
import models.entities.Phone;
import settings.DatabaseServer;

public class PhoneData {

	ObjectContainer phoneData;
	/*
	 * Constructors	
	 */
	public PhoneData() {
		phoneData = DatabaseServer.getServer().openClient();
	}
	
	public PhoneData(ObjectContainer phoneData) {
		super();
		this.phoneData = phoneData;
	}

	/**
	 * @return the customerData
	 */
	public ObjectContainer getPhoneData() {
		return phoneData;
	}

	/**
	 * @param PhoneData the phoneData to set
	 */
	public void setPhoneData(ObjectContainer PhoneData) {
		this.phoneData = PhoneData;
	}
	
	/*
	 * Public Methods
	 */
	public boolean phoneAdd(Phone phone) {
		if(!phoneExists(String.valueOf(phone.getNumber()))) {
			phoneData.store(phone);
			phoneData.commit();
			return true;
		}
		return false;
	}
	
	public boolean phoneRemove(Phone phone) {
		if(phoneExists(String.valueOf(phone.getNumber()))) {
			phoneData.delete(phone);
			phoneData.commit();
			return true;
		}
		return false;
	}

	protected boolean phoneExists(String phone) {
		return this.getPhoneByNumber(phone)==null?false:true;
	}
	
	public Customer getPhoneByNumber(String phoneNumber) {
		Query query = phoneData.query();
		query.constrain(Phone.class);
		query.descend("number").constrain(phoneNumber).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext()?result.next():null;
	}
	
	public ObjectSet<Customer> getPhones(){
		Query query = phoneData.query();
		query.constrain(Customer.class);
		return query.execute();
	}

}
