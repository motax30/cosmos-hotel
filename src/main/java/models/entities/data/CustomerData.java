package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import models.entities.Customer;
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
	
	public boolean customerRemove(Customer customer) {
		if(customerExists(customer.getCpf())) {
			customerData.delete(customer);
			customerData.commit();
			return true;
		}
		return false;
	}

	protected boolean customerExists(String customerName) {
		return this.getCustomerByCustomerName(customerName)==null?false:true;
	}
	protected boolean customerExists(Integer cpfCustomer) {
		return this.getCustomerByCustomerCpf(cpfCustomer)==null?false:true;
	}

	private Object getCustomerByCustomerCpf(Integer cpfCustomer) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("cpf").constrain(cpfCustomer).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext()?result.next():null;
	}
	
	private Object getCustomerByCustomerName(String customerName) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("name").constrain(customerName).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext()?result.next():null;
	}
	
	public ObjectSet<Customer> getCustomers(){
		Query query = customerData.query();
		query.constrain(Customer.class);
		return query.execute();
	}

}
