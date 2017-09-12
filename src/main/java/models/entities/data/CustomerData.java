package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Address;
import models.entities.Customer;
import models.entities.Receptionist;
import settings.DatabaseServer;

@Data
@AllArgsConstructor
public class CustomerData {
	private ObjectContainer customerData;

	public CustomerData() {
		customerData = DatabaseServer.getServer().openClient();
	}

	/*
	 * Public Methods
	 */
	public boolean customerAdd(Customer customer) {
		if(!customerExists(customer.getName())) {
			if (customer.getAddress() != null) {
				customerData.store(customer.getAddress());
			}
			customerData.store(customer);
			customerData.commit();
			return true;
		}
		return false;
	}
	
	public boolean customerRemove(Customer customer) {
		if(customerByCpfExists(customer.getCpfNumber())) {
			customerData.delete(customer);
			customerData.commit();
			return true;
		}
		return false;
	}

	public boolean customerExists(String name) {
		return this.getCustomerByCustomerName(name) != null;
	}
	public boolean customerByCpfExists(String cpfNumber) {
		return this.getCustomerByCustomerCpf(cpfNumber) != null;
	}

	public Customer getCustomerById(String id) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("id").constrain(id).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}

	public Customer customerUpdate(Customer customer) {
		Customer currentCustomer = getCustomerById(customer.getId());
		currentCustomer.setName(customer.getName());
		currentCustomer.setNotes(customer.getNotes());
		customerData.store(currentCustomer);
		customerData.commit();
		return currentCustomer;
	}

	public Customer getCustomerByCustomerCpf(String cpfNumber) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("cpfNumber").constrain(cpfNumber).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	public Customer getCustomerByCustomerName(String name) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("name").constrain(name).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	public ObjectSet<Customer> getCustomers(){
		Query query = customerData.query();
		query.constrain(Customer.class);
		return query.execute();
	}
}
