package models.entities.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.db4o.ObjectSet;
import com.db4o.collections.ArrayList4;

import models.entities.Address;
import models.entities.Customer;
import models.entities.Phone;
import models.entities.data.CustomerData;
import models.enumerates.PhoneType;
import settings.DatabaseServerTest;

public class CustomerDataTest {
	
	private CustomerData customerData = new CustomerData(DatabaseServerTest.getServer().openClient());
	private List<Phone<Customer>> phones;
	Phone<Customer> phone1;
	Phone<Customer> phone2;
	Phone<Customer> phone3;
	private Customer customerComTodosOsDados;
	private Customer customer;
	private Address end1;
	private Address end2;
	private Address end3;

	@Before
	public void setUp() {
		end1 = new Address("1","Rua 1","123","fundos","esplanada","12756712","São José","sp","Brasil");
		end2 = new Address("2","Rua 2","321","fundos","jardim americano","12756712","São José","sp","Brasil");
		end3 = new Address("3","Rua 3","456","fundos","por do sol","12756712","São José","sp","Brasil");
	}

	@Test
	public void testCustomerAdd() {
		customer = new Customer();
		assertTrue(customerData.customerAdd(customer));
		customerData.customerRemove(customer);
	}

	@Test
	public void testCustomerRemove() {
		phones = new ArrayList4<>();
		phones.add(new Phone<Customer>("1", PhoneType.CELLULAR, "1111111", customerComTodosOsDados));
		phones.add(new Phone<Customer>("1", PhoneType.HOME, "32456789", customerComTodosOsDados));
		phones.add(new Phone<Customer>("1", PhoneType.WORK, "34561234", customer));
		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
		customerData.customerAdd(customerComTodosOsDados);
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testCustomerSearchFromNameAndNotFound() {
		phones = new ArrayList4<>();
		phones.add(new Phone<Customer>("1", PhoneType.CELLULAR, "1111111", customerComTodosOsDados));
		phones.add(new Phone<Customer>("1", PhoneType.HOME, "32456789", customerComTodosOsDados));
		phones.add(new Phone<Customer>("1", PhoneType.WORK, "34561234", customer));
		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
		assertFalse("the customerName should not be found",customerData.customerExists(customerComTodosOsDados.getName()));
	}
	
	@Test
	public void testCustomerSearchFromNameAndReturnWithSuccess() {
		phones = new ArrayList4<>();
		phones.add(new Phone<Customer>("1", PhoneType.CELLULAR, "1111111", customerComTodosOsDados));
		phones.add(new Phone<Customer>("1", PhoneType.HOME, "32456789", customerComTodosOsDados));
		phones.add(new Phone<Customer>("1", PhoneType.WORK, "34561234", customer));
		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
		customerData.customerAdd(customerComTodosOsDados);
		assertTrue("the customerName be found",customerData.customerExists(customerComTodosOsDados.getName()));
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testCustomerSearchFromCpfAndNotFound() {
		phones = new ArrayList4<>();
		phones.add(new Phone<Customer>("1", PhoneType.CELLULAR, "1111111", customerComTodosOsDados));
		phones.add(new Phone<Customer>("1", PhoneType.HOME, "32456789", customerComTodosOsDados));
		phones.add(new Phone<Customer>("1", PhoneType.WORK, "34561234", customer));
		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
		assertFalse("the cpfNumber of customer should not be found",customerData.customerByCpfExists(customerComTodosOsDados.getName()));
	}
	
	@Test
	public void testCustomerSearchFromCpfAndReturnWithSuccess() {
		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
		customerData.customerAdd(customerComTodosOsDados);
		assertTrue("the cpfNumber of customer should be found",customerData.customerExists(customerComTodosOsDados.getName()));
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testGetCustomerByCustomerCpf() {
		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
		customerData.customerAdd(customerComTodosOsDados);
		Customer customerRetornado = customerData.getCustomerByCustomerCpf(customerComTodosOsDados.getCpfNumber());
		assertEquals(customerComTodosOsDados,customerRetornado);
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testGetCustomerByCustomerName() {
		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
		customerData.customerAdd(customerComTodosOsDados);
		assertEquals("the customerName should not be found",customerComTodosOsDados,customerData.getCustomerByCustomerName(customerComTodosOsDados.getName()));
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testGetCustomers() {
		customer = new Customer();
		customer.setName("nomeTeste1");
		customerData.customerAdd(customer);
		ObjectSet<Customer> customers = customerData.getCustomers();
		while(customers.hasNext()) {
			Customer customerRetornado = customers.next();
			assertEquals(customerData.getCustomerByCustomerName(customerRetornado.getName()), customerRetornado);
		}
	}
}