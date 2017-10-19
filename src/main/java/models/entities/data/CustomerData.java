package models.entities.data;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.NoArgsConstructor;
import models.entities.Customer;
import models.entities.Phone;
import models.util.GenericOperationsBdImpl;

@NoArgsConstructor
public class CustomerData extends GenericOperationsBdImpl implements Datable<Customer, Customer,String>{
	
	public CustomerData(String escope) {
		openBd(escope);
	}

	private void isBdNullOrClosed(String escope) {
		if(bd==null||bd.ext().isClosed()) {
			openBd(escope);
		}
	}
	/*
	 * Public Methods
	 */
	@Override
	public boolean create(Customer customer,String escope) {
		isBdNullOrClosed(escope);
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
		openBd(escope);
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
		openBd(escope);
		for(Customer customer : findAll(escope)) {
			deletarEntidadeBd(customer);
		}
		closeBd();
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
	public ObjectSet<Customer> findAll(String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Customer.class);
		return query.execute();
	}
	
	@Override
	public ObjectSet<Customer> findAllBy(String key, String value,String escope) {
		isBdNullOrClosed(escope);
		Query query = bd.query();
		query.constrain(Customer.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}

	@Override
	public Customer findBy(String entity, String entity2, String escope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectSet<Customer> findAllBy(String value, String escope) {
		// TODO Auto-generated method stub
		return null;
	}
}