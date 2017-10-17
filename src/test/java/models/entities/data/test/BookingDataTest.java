package models.entities.data.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.entities.Accommodation;
import models.entities.AccommodationTypeInformations;
import models.entities.Address;
import models.entities.Booking;
import models.entities.Customer;
import models.entities.Phone;
import models.entities.Receptionist;
import models.entities.data.BookingData;
import models.enumerates.PaymentOptions;
import models.enumerates.PhoneType;
import models.enumerates.ReceptionistType;
import models.enumerates.StatusBooking;
import settings.DatabaseServerTest;

public class BookingDataTest {
	private static final LocalDateTime BookingInitialDate = null;
	private static final LocalDateTime BookingFinalDate = null;
	Booking booking;
	private BookingData bookingData = new BookingData();
	private Accommodation accommodation1;
	private Accommodation accommodation2;
	private Customer customer1 = new Customer();
	private Customer customer2 = new Customer();
	private List<Phone<Customer>> phones;
	private Address address;
	private LocalDateTime birthday;
	private Receptionist recepcionist;
	private Booking booking2;
	@Before
	public void setUp() throws Exception {
		phones = new ArrayList<>();
		phones.add(new Phone<Customer>("1", PhoneType.HOME, "30987877", customer1,LocalDateTime.now(),LocalDateTime.now()));
		phones.add(new Phone<Customer>("2", PhoneType.CELLULAR, "987651238", customer1,LocalDateTime.now(),LocalDateTime.now()));
		phones.add(new Phone<Customer>("", PhoneType.HOME, "32345432", customer1,LocalDateTime.now(),LocalDateTime.now()));
		address = new Address("1", "Rua 1", "123","home", "jardim Americado", "123345678", "São Jose dos Campos", "sp", "Brasil");
		customer1 = new Customer("1", "15287269951", "joao", "joaopedro@email.com", phones, address, "cliente vip", birthday.now(),LocalDateTime.now(),LocalDateTime.now());
		customer1 = new Customer("2", "22222222222", "pedro", "pedro@email.com", phones, address, "cliente vip", birthday.now(),LocalDateTime.now(),LocalDateTime.now());
		accommodation1 = new Accommodation("duplo", new AccommodationTypeInformations(123.00, 2,"1"), false, LocalDateTime.now(),LocalDateTime.now());
		accommodation2 = new Accommodation("simples", new AccommodationTypeInformations(223.00, 2,"2"),false,LocalDateTime.now(),LocalDateTime.now());
		recepcionist = new Receptionist("1", "admin", "admin@fatec.br", "admin", ReceptionistType.RECEPCIONIST, "Joana", "notes", LocalDateTime.now(),LocalDateTime.now());
	}

	@After
	public void tearDown() throws Exception {
		DatabaseServerTest.deleteTestDatabase();
	}

	@Test
	public void testBookingAddWithAllData() throws IOException {
		Customer customer;
		LocalDateTime checkInDate = LocalDateTime.of(LocalDate.of(2017, 05, 20),LocalTime.now());
		LocalDateTime checkOutDate = LocalDateTime.of(LocalDate.of(2017, 05, 20),LocalTime.now());
		LocalDateTime createdAt = LocalDateTime.of(LocalDate.of(2017, 05, 20),LocalTime.now());
		LocalDateTime updateAt = LocalDateTime.of(LocalDate.of(2017, 05, 20),LocalTime.now());
		booking = new Booking(customer1, accommodation1, recepcionist,
							accommodation1.getAccommodationTypeInformations().getDailyPrice(),
							StatusBooking.BLANK.toString(), checkInDate, checkOutDate, 
							BookingInitialDate, BookingFinalDate,123.34, PaymentOptions.IN_CASH.toString(),123.45,createdAt,updateAt);
		booking2 = new Booking(customer2, accommodation1, recepcionist,
				accommodation2.getAccommodationTypeInformations().getDailyPrice(),
				StatusBooking.BLANK.toString(), checkInDate, checkOutDate, 
				BookingInitialDate, BookingFinalDate,300.12, PaymentOptions.IN_CASH.toString(),111.45,createdAt,updateAt);
		assertTrue(bookingData.create(booking));
	}

}
