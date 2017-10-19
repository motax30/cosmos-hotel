package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.entities.Customer;
import models.entities.Phone;
import models.util.GenericOperationsBdImpl;

import org.springframework.beans.BeanUtils;
import settings.DatabaseServer;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData extends GenericOperationsBdImpl implements Datable<Customer, Customer,String>{
	private ObjectContainer bd;

	public CustomerData(String escope) {
		openBd(escope);
	}

	/*
	 * Public Methods
	 */
	@Override
	public boolean create(Customer customer,String escope) {
		openBd(escope);
		if (exists("cpfNumber", customer.getCpfNumber()) || exists("email", customer.getEmail())) {
			return false;
		}

		customer.setCreatedAt(LocalDateTime.now());
		customer.setUpdatedAt(LocalDateTime.now());

		if (customer.getAddress() != null) {
			bd.store(customer.getAddress());
		}

		if (customer.getPhones() != null) {
			for (Phone<Customer> phone : customer.getPhones()) {
				bd.store(phone);
			}
		}

		gravarBd(customer);
		closeBd();
		return true;
	}
	
	@Override
	public Customer update(Customer customer,String escope) {
		openBd(escope);
		Customer currentCustomer = findBy("id", customer.getId());
		LocalDateTime createdAt = currentCustomer.getCreatedAt();

		BeanUtils.copyProperties(customer, currentCustomer);
		currentCustomer.setUpdatedAt(LocalDateTime.now());
		currentCustomer.setCreatedAt(createdAt);

		gravarBd(currentCustomer);
		closeBd();
		return currentCustomer;
	}
	
	@Override
	public boolean delete(Customer customer,String escope) {
		try {
			deletarEntidadeBd(customer);
			closeBd();
			return true;
		} catch (Exception error) {
			return false;
		}
	}
	
	@Override
	public void deleteAll(String escope) {
		for(Customer customer : findAll()) {
			deletarEntidadeBd(customer);
			closeBd();
		}
	}
	
	@Override
	public boolean exists(String key, String value) {
		return findBy(key, value) != null;
	}
	
	@Override
	public Customer findBy(String key, String value) {
		Query query = bd.query();
		query.constrain(Customer.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Customer> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	@Override
	public ObjectSet<Customer> findAll() {
		Query query = bd.query();
		query.constrain(Customer.class);
		return query.execute();
	}
	
	@Override
	public ObjectSet<Customer> findAllBy(String key, String value) {
		Query query = bd.query();
		query.constrain(Customer.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}

	@Override
	public Customer findBy(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectSet<Customer> findAllBy(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}