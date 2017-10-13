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
public class CustomerData implements Datable<Customer, Customer,String>{
	private ObjectContainer customerData;

	public CustomerData() {
		customerData = DatabaseServer.getServer().openClient();
	}

	/*
	 * Public Methods
	 */
	@Override
	public boolean create(Customer customer) {
		if (exists("cpfNumber", customer.getCpfNumber()) || exists("email", customer.getEmail())) {
			return false;
		}

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
	
	@Override
	public Customer update(Customer customer) {
		Customer currentCustomer = findBy("id", customer.getId());
		LocalDateTime createdAt = currentCustomer.getCreatedAt();

		BeanUtils.copyProperties(customer, currentCustomer);
		currentCustomer.setUpdatedAt(LocalDateTime.now());
		currentCustomer.setCreatedAt(createdAt);

		customerData.store(currentCustomer);
		customerData.commit();

		return currentCustomer;
	}
	
	@Override
	public boolean delete(Customer customer) {
		try {
			customerData.delete(customer);
			customerData.commit();
			return true;
		} catch (Exception error) {
			return false;
		}
	}
	
	@Override
	public void deleteAll() {
		for(Customer customer : findAll()) {
			customerData.delete(customer);
			customerData.commit();
		}
	}
	
	@Override
	public boolean exists(String key, String value) {
		return findBy(key, value) != null;
	}
	
	@Override
	public Customer findBy(String key, String value) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	@Override
	public ObjectSet<Customer> findAll() {
		Query query = customerData.query();
		query.constrain(Customer.class);
		return query.execute();
	}
	
	@Override
	public ObjectSet<Customer> findAllBy(String key, String value) {
		Query query = customerData.query();
		query.constrain(Customer.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}
}