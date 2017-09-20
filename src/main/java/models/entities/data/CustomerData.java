package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Customer;
import models.entities.Phone;
import org.springframework.beans.BeanUtils;
import settings.DatabaseServer;

import java.time.LocalDateTime;

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
			customer.setCreatedAt(LocalDateTime.now());
			customer.setUpdatedAt(LocalDateTime.now());
			if (customer.getAddress() != null) {
				customerData.store(customer.getAddress());
			}

			if (customer.getPhones() != null) {
				for (Phone<Customer> phone : customer.getPhones()) {
					customerData.store(phone);
				}
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
		LocalDateTime createdAt = currentCustomer.getCreatedAt();

		BeanUtils.copyProperties(customer, currentCustomer);
		currentCustomer.setUpdatedAt(LocalDateTime.now());
		currentCustomer.setCreatedAt(createdAt);
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

	public ObjectSet<Customer> getCustomersByEmail(String email){
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("email").constrain(email).equal();
		return query.execute();
	}

	public ObjectSet<Customer> getCustomersByCustomerCpf(String cpfNumber) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend("cpfNumber").constrain(cpfNumber).equal();
		ObjectSet<Customer> result = query.execute();
		return query.execute();
	}
}
