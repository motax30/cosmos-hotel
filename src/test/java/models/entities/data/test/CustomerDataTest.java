package models.entities.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.db4o.ObjectSet;
import com.db4o.collections.ArrayList4;

import models.entities.Address;
import models.entities.Customer;
import models.entities.Phone;
import models.entities.data.CustomerData;
import models.enumerates.PhoneType;
import settings.DatabaseServer;
import settings.DatabaseServerTest;

public class CustomerDataTest {
	
	private static CustomerData customerData;
	private Customer customer;

	@BeforeClass
	public static void setUpClass() {
		customerData = new CustomerData();
		customerData.setObjectContainer(DatabaseServerTest.getServer().openClient());
		/* Delete All Customers */
		customerData.deleteAll();
	}

	@Before
	public void setUp() {
		/* Customer */
		Customer customer = new Customer();
		customer.setName("Dhaval Tighe");
		customer.setCpfNumber("531.155.728-16");
		customer.setEmail("dhavaval@tighe.com");
		customer.setNotes("Excellent customer!");
		/* Address */
		Address address = new Address();
		address.setZipCode("01311-200");
		address.setStreet("Avenida Paulista");
		address.setNumber("1047");
		address.setNeighborhood("Bela Vista");
		address.setCity("São Paulo");
		address.setState("SP");
		customer.setAddress(address);
		/* Phones */
		Phone<Customer> firstPhone = new Phone<>();
		firstPhone.setNumber("(11) 97123-1234");
		firstPhone.setType(PhoneType.CELLULAR);
		Phone<Customer> secondPhone = new Phone<>();
		secondPhone.setNumber("(11) 98432-1234");
		secondPhone.setType(PhoneType.CELLULAR);
		List<Phone<Customer>> phones = new ArrayList<>();
		phones.add(firstPhone);
		phones.add(secondPhone);
		customer.setPhones(phones);

		this.customer = customer;
	}
	/* Test: test add method */
	@Test
	public void testAdd() {
		boolean isSaved = customerData.create(this.customer);
		assertEquals(true, isSaved);
	}

	@Test
	public void testUpdate() {
		/* Avoid problem with test order */
		setUp();
		customerData.create(this.customer);

		Customer customer = customerData.findBy("cpfNumber", this.customer.getCpfNumber());
		customer.setName("Dhaval Tighe Tower");
		customer.setCpfNumber("954.504.632-51");
		customer.setEmail("dhavaval@tighe.com");
		customer.setNotes("Excellent customer!");

		Customer updatedCustomer = customerData.update(customer);
		assertEquals(customer, updatedCustomer);
	}

	@Test
	public void testRemove() {
		Customer customer = customerData.findBy("cpfNumber", this.customer.getCpfNumber());
		boolean isRemoved = customerData.delete(customer);
		assertEquals(true, isRemoved);
	}

	@Test
	public void testExists() {
		/* Avoid problem with test order */
		setUp();
		customerData.create(this.customer);

		/* Need to get current UUID */
		Customer customer = customerData.findBy("cpfNumber", this.customer.getCpfNumber());

		assertEquals(true, customerData.exists("id", customer.getId()));
		assertEquals(true, customerData.exists("cpfNumber", customer.getCpfNumber()));
		assertEquals(true, customerData.exists("email", customer.getEmail()));
		assertEquals(true, customerData.exists("name", customer.getName()));
	}

	@Test
	public void testFindBy() {
		/* Need to get current UUID */
		Customer customer = customerData.findBy("cpfNumber", this.customer.getCpfNumber());

		assertEquals(customer, customerData.findBy("id", customer.getId()));
		assertEquals(customer, customerData.findBy("cpfNumber", customer.getCpfNumber()));
		assertEquals(customer, customerData.findBy("email", customer.getEmail()));
		assertEquals(customer, customerData.findBy("name", customer.getName()));
	}

	@Test
	public void testFindAll() {
		ObjectSet<Customer> customers = customerData.findAll();
		assertEquals(true, customers.size() > 0);
	}

	@Test
	public void testFindAllBy() {
		ObjectSet<Customer> customers = customerData.findAllBy("email", this.customer.getEmail());
		assertEquals(true, customers.size() > 0);
		for (Customer customer : customers) {
			assertEquals(this.customer.getEmail(), customer.getEmail());
		}
	}
}