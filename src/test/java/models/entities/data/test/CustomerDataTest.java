package models.entities.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
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
	private ObjectSet<Customer> customers;
	private List<Address> address;
	
	@Before
	public void setUp() {
		//Created a new Customer
		phones = new ArrayList4<>();
		phones.add(new Phone<Customer>(PhoneType.CEL,1111111));
		phones.add(new Phone<Customer>(PhoneType.CEL,1111111));
		phones.add(new Phone<Customer>(PhoneType.CEL,1111111));
		address = new ArrayList4<>();
		Address end1 = new Address();
		end1.setStreet("Endereço 1");
		Address end2 = new Address();
		end2.setStreet("Endereço 2");
		address.add(end1);
		address.add(end2);
	}

	@Test
	public void testCustomerAdd() {
		customer = new Customer();
		assertTrue(customerData.customerAdd(customer));
		customerData.customerRemove(customer);
	}

	@Test
	public void testCustomerRemove() {
		customerComTodosOsDados = new Customer("1","33345555511","teste", phones,address,"qualquer informação sobre o Customer",LocalDate.now());
		customerData.customerAdd(customerComTodosOsDados);
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testCustomerSearchFromNameAndNotFound() {
		customerComTodosOsDados = new Customer("1","33345555511","teste", phones,address,"qualquer informação sobre o Customer",LocalDate.now());
		assertFalse("the customerName should not be found",customerData.customerExists(customerComTodosOsDados.getName()));
	}
	
	@Test
	public void testCustomerSearchFromNameAndReturnWithSuccess() {
		customerComTodosOsDados = new Customer("1","33345555511","teste", phones,address,"qualquer informação sobre o Customer",LocalDate.now());
		customerData.customerAdd(customerComTodosOsDados);
		assertTrue("the customerName be found",customerData.customerExists(customerComTodosOsDados.getName()));
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testCustomerSearchFromCpfAndNotFound() {
		customerComTodosOsDados = new Customer("1","33345555511","teste", phones,address,"qualquer informação sobre o Customer",LocalDate.now());
		assertFalse("the cpfNumber of customer should not be found",customerData.customerByCpfExists(customerComTodosOsDados.getName()));
	}
	
	@Test
	public void testCustomerSearchFromCpfAndReturnWithSuccess() {
		customerComTodosOsDados = new Customer("1","33345555511","teste", phones,address,"qualquer informação sobre o Customer",LocalDate.now());
		customerData.customerAdd(customerComTodosOsDados);
		assertTrue("the cpfNumber of customer should be found",customerData.customerExists(customerComTodosOsDados.getName()));
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testGetCustomerByCustomerCpf() {
		customerComTodosOsDados = new Customer("1","33345555511","teste", phones,address,"qualquer informação sobre o Customer",LocalDate.now());
		customerData.customerAdd(customerComTodosOsDados);
		Customer customerRetornado = customerData.getCustomerByCustomerCpf(customerComTodosOsDados.getCpfNumber());
		assertEquals(customerComTodosOsDados,customerRetornado);
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testGetCustomerByCustomerName() {
		customerComTodosOsDados = new Customer("1","33345555511","teste", phones,address,"qualquer informação sobre o Customer",LocalDate.now());
		customerData.customerAdd(customerComTodosOsDados);
		assertEquals("the customerName should not be found",customerComTodosOsDados,customerData.getCustomerByCustomerName(customerComTodosOsDados.getName()));
		customerData.customerRemove(customerComTodosOsDados);
	}

	@Test
	public void testGetCustomers() {
		customer = new Customer();
		customer.setName("nomeTeste1");
		customerData.customerAdd(customer);
		customers = customerData.getCustomers();
		while(customers.hasNext()) {
			Customer customerRetornado = customers.next();
			assertEquals(customerData.getCustomerByCustomerName(customerRetornado.getName()), customerRetornado);
		}
	}
}