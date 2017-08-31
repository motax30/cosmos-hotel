package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import models.entities.Customer;
import models.entities.Receptionist;
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
		if(customerByCpfExists(customer.getCpf())) {
			customerData.delete(customer);
			customerData.commit();
			return true;
		}
		return false;
	}

	public boolean customerExists(String customerName) {
		return this.getCustomerByCustomerName(customerName)==null?false:true;
	}
	public boolean customerByCpfExists(String cpfCustomer) {
		return this.getCustomerByCustomerCpf(cpfCustomer)==null?false:true;
	}

	public Customer getCustomerByCustomerCpf(String customerCpf) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("cpf_number").constrain(customerCpf).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext()?result.next():null;
	}
	
	public Customer getCustomerByCustomerName(String customerName) {
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
