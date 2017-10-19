package models.entities.data.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.db4o.ObjectSet;

import models.entities.Address;
import models.entities.Customer;
import models.entities.Phone;
import models.entities.data.CustomerData;
import models.enumerates.PhoneType;
import models.enumerates.Scope;

public class CustomerDataTest {
	
	private static CustomerData bd = new CustomerData(Scope.TESTE.toString());
	private Customer customer;

	//	private Customer customerComTodosOsDados;
	//	Phone<Customer> phone3;
	//	Phone<Customer> phone2;
	//	Phone<Customer> phone1;
//	private List<Phone<Customer>> phones;
//	private Address end1;
//	private Address end2;
//	private Address end3;
//
//	@Before
//	public void setUp() {
//		end1 = new Address("1","Rua 1","123","fundos","esplanada","12756712","São José","sp","Brasil");
//		end2 = new Address("2","Rua 2","321","fundos","jardim americano","12756712","São José","sp","Brasil");
//		end3 = new Address("3","Rua 3","456","fundos","por do sol","12756712","São José","sp","Brasil");
//	}
//
//	@Test
//	public void testCustomerAdd() {
//		customer = new Customer();
//		assertTrue(customerData.customerAdd(customer));
//		customerData.customerRemove(customer);
//	}
//
//	@Test
//	public void testCustomerRemove() {
//		phones = new ArrayList4<>();
//		phones.add(new Phone<Customer>("1", PhoneType.CELLULAR, "1111111", customerComTodosOsDados));
//		phones.add(new Phone<Customer>("1", PhoneType.HOME, "32456789", customerComTodosOsDados));
//		phones.add(new Phone<Customer>("1", PhoneType.WORK, "34561234", customer));
//		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//		customerData.customerAdd(customerComTodosOsDados);
//		customerData.customerRemove(customerComTodosOsDados);
//	}
//
//	@Test
//	public void testCustomerSearchFromNameAndNotFound() {
//		phones = new ArrayList4<>();
//		phones.add(new Phone<Customer>("1", PhoneType.CELLULAR, "1111111", customerComTodosOsDados));
//		phones.add(new Phone<Customer>("1", PhoneType.HOME, "32456789", customerComTodosOsDados));
//		phones.add(new Phone<Customer>("1", PhoneType.WORK, "34561234", customer));
//		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//		assertFalse("the customerName should not be found",customerData.customerExists(customerComTodosOsDados.getName()));
//	}
//
//	@Test
//	public void testCustomerSearchFromNameAndReturnWithSuccess() {
//		phones = new ArrayList4<>();
//		phones.add(new Phone<Customer>("1", PhoneType.CELLULAR, "1111111", customerComTodosOsDados));
//		phones.add(new Phone<Customer>("1", PhoneType.HOME, "32456789", customerComTodosOsDados));
//		phones.add(new Phone<Customer>("1", PhoneType.WORK, "34561234", customer));
//		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//		customerData.customerAdd(customerComTodosOsDados);
//		assertTrue("the customerName be found",customerData.customerExists(customerComTodosOsDados.getName()));
//		customerData.customerRemove(customerComTodosOsDados);
//	}
//
//	@Test
//	public void testCustomerSearchFromCpfAndNotFound() {
//		phones = new ArrayList4<>();
//		phones.add(new Phone<Customer>("1", PhoneType.CELLULAR, "1111111", customerComTodosOsDados));
//		phones.add(new Phone<Customer>("1", PhoneType.HOME, "32456789", customerComTodosOsDados));
//		phones.add(new Phone<Customer>("1", PhoneType.WORK, "34561234", customer));
//		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//		assertFalse("the cpfNumber of customer should not be found",customerData.customerByCpfExists(customerComTodosOsDados.getName()));
//	}
//
//	@Test
//	public void testCustomerSearchFromCpfAndReturnWithSuccess() {
//		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//		customerData.customerAdd(customerComTodosOsDados);
//		assertTrue("the cpfNumber of customer should be found",customerData.customerExists(customerComTodosOsDados.getName()));
//		customerData.customerRemove(customerComTodosOsDados);
//	}
//
//	@Test
//	public void testGetCustomerByCustomerCpf() {
//		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//		customerData.customerAdd(customerComTodosOsDados);
//		Customer customerRetornado = customerData.getCustomerByCustomerCpf(customerComTodosOsDados.getCpfNumber());
//		assertEquals(customerComTodosOsDados,customerRetornado);
//		customerData.customerRemove(customerComTodosOsDados);
//	}
//
//	@Test
//	public void testGetCustomerByCustomerName() {
//		customerComTodosOsDados = new Customer("1","33345555511","teste","email@sistema.com", phones, end1, "anotação", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//		customerData.customerAdd(customerComTodosOsDados);
//		assertEquals("the customerName should not be found",customerComTodosOsDados,customerData.getCustomerByCustomerName(customerComTodosOsDados.getName()));
//		customerData.customerRemove(customerComTodosOsDados);
//	}
//
//	@Test
//	public void testGetCustomers() {
//		customer = new Customer();
//		customer.setName("nomeTeste1");
//		customerData.customerAdd(customer);
//		ObjectSet<Customer> customers = customerData.getCustomers();
//		while(customers.hasNext()) {
//			Customer customerRetornado = customers.next();
//			assertEquals(customerData.getCustomerByCustomerName(customerRetornado.getName()), customerRetornado);
//		}
//	}

	@BeforeClass
	public static void setUpClass() {
		/* Delete All Customers */
		bd.deleteAll(Scope.TESTE.toString());
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
		boolean isSaved = bd.create(this.customer,Scope.TESTE.toString());
		assertEquals(true, isSaved);
	}

	@Test
	public void testUpdate() {
		/* Avoid problem with test order */
		setUp();
		bd.create(this.customer,Scope.TESTE.toString());

		Customer customer = bd.findBy("cpfNumber", this.customer.getCpfNumber());
		customer.setName("Dhaval Tighe Tower");
		customer.setCpfNumber("954.504.632-51");
		customer.setEmail("dhavaval@tighe.com");
		customer.setNotes("Excellent customer!");

		Customer updatedCustomer = bd.update(customer,Scope.TESTE.toString());
		assertEquals(customer, updatedCustomer);
	}

	@Test
	public void testRemove() {
		Customer customer = bd.findBy("cpfNumber", this.customer.getCpfNumber());
		boolean isRemoved = bd.delete(customer,Scope.TESTE.toString());
		assertEquals(true, isRemoved);
	}

	@Test
	public void testExists() {
		/* Avoid problem with test order */
		setUp();
		bd.create(this.customer,Scope.TESTE.toString());

		/* Need to get current UUID */
		Customer customer = bd.findBy("cpfNumber", this.customer.getCpfNumber());

		assertEquals(true, bd.exists("id", customer.getId()));
		assertEquals(true, bd.exists("cpfNumber", customer.getCpfNumber()));
		assertEquals(true, bd.exists("email", customer.getEmail()));
		assertEquals(true, bd.exists("name", customer.getName()));
	}

	@Test
	public void testFindBy() {
		/* Need to get current UUID */
		Customer customer = bd.findBy("cpfNumber", this.customer.getCpfNumber());

		assertEquals(customer, bd.findBy("id", customer.getId()));
		assertEquals(customer, bd.findBy("cpfNumber", customer.getCpfNumber()));
		assertEquals(customer, bd.findBy("email", customer.getEmail()));
		assertEquals(customer, bd.findBy("name", customer.getName()));
	}

	@Test
	public void testFindAll() {
		ObjectSet<Customer> customers = bd.findAll(Scope.TESTE.toString());
		assertEquals(true, customers.size() > 0);
	}

	@Test
	public void testFindAllBy() {
		ObjectSet<Customer> customers = bd.findAllBy("email", this.customer.getEmail());
		assertEquals(true, customers.size() > 0);
		for (Customer customer : customers) {
			assertEquals(this.customer.getEmail(), customer.getEmail());
		}
	}
}