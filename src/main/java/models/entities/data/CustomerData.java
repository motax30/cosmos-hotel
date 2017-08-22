package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import models.entities.Customer;
import models.entities.User;
import settings.DatabaseServer;

public class CustomerData {
	ObjectContainer customerData;
	/*
	 * Constructors	
	 */
	public CustomerData() {
		customerData = DatabaseServer.getServer().openClient();
	}
	
	public CustomerData(ObjectContainer customerData) {
		super();
		this.customerData = customerData;
	}

	/**
	 * @return the customerData
	 */
	public ObjectContainer getCustomerData() {
		return customerData;
	}

	/**
	 * @param customerData the customerData to set
	 */
	public void setCustomerData(ObjectContainer customerData) {
		this.customerData = customerData;
	}
	
	/*
	 * Public Methods
	 */
	public boolean customerAdd(Customer customer) {
		if(!customerExists(customer.getName())) {
			customerData.store(customer);
			customerData.commit();
			return true;
		}
		return false;
	}

	private boolean customerExists(String customerName) {
		return this.getCustomerByCustomerName(customerName)==null?false:true;
	}

	private Object getCustomerByCustomerName(String customerName) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("name").constrain(customerName).equal();
		ObjectSet<User> result = query.execute();
		return result.hasNext()?result.next():null;
	}
	
	public ObjectSet<Customer> getCustomers(){
		Query query = customerData.query();
		query.constrain(Customer.class);
		return query.execute();
	}

}
