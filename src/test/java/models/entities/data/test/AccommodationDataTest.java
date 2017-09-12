package models.entities.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.db4o.ObjectSet;

import models.entities.Accommodation;
import models.entities.AccommodationTypeInformations;
import models.entities.data.AccommodationData;
import settings.DatabaseServerTest;

public class AccommodationDataTest {
	private AccommodationData accommodationData = new AccommodationData(DatabaseServerTest.getServer().openClient());
	private Accommodation accommodation;
	private Accommodation accommodation2;
	private AccommodationTypeInformations accommodationTypeInformations;
	private AccommodationTypeInformations accommodationTypeInformations2;
	
	
	@Before
	public void setUp() {
		accommodation = new Accommodation();
		accommodation2 = new Accommodation();
	}

	@Test
	public void testAddAccommodation() {
		accommodationTypeInformations = new AccommodationTypeInformations(120,3);
		accommodation.setAccommodationTypeInformations(accommodationTypeInformations);
		accommodation.setTypeAccommodation("duplo");
		assertTrue(accommodationData.accommodationAdd(accommodation));
		accommodationData.accommodationRemove(accommodation);
	}
	
	@Test
	public void testAddAccommodationExists() {
		accommodationTypeInformations = new AccommodationTypeInformations(120,3);
		accommodation.setAccommodationTypeInformations(accommodationTypeInformations);
		accommodation.setTypeAccommodation("duplo");
		accommodationData.accommodationAdd(accommodation);
		accommodationTypeInformations = new AccommodationTypeInformations(120,3);
		accommodation.setAccommodationTypeInformations(accommodationTypeInformations);
		accommodation.setTypeAccommodation("duplo");
		assertFalse(accommodationData.accommodationAdd(accommodation));
		accommodationData.accommodationRemove(accommodation);
	}
	
	@Test
	public void testRemoveAccommodation() {
		accommodationTypeInformations = new AccommodationTypeInformations(120,3);
		accommodation.setAccommodationTypeInformations(accommodationTypeInformations);
		accommodation.setTypeAccommodation("duplo");
		accommodationData.accommodationAdd(accommodation);
		assertTrue(accommodationData.accommodationRemove(accommodation));
		accommodationData.accommodationRemove(accommodation);
	}
	
	@Test
	public void testGetAccommodations() {
		accommodationTypeInformations = new AccommodationTypeInformations(111,3);
		accommodation.setAccommodationTypeInformations(accommodationTypeInformations);
		accommodation.setTypeAccommodation("duplo");
		accommodationData.accommodationAdd(accommodation);
		accommodationTypeInformations2 = new AccommodationTypeInformations(150,2);
		accommodation2.setAccommodationTypeInformations(accommodationTypeInformations2);
		accommodation2.setTypeAccommodation("simples");
		accommodationData.accommodationAdd(accommodation2);
		ObjectSet<Accommodation>accommodations = accommodationData.getAccommodations();
		while(accommodations.hasNext()) {
			Accommodation res = accommodations.next();
			assertEquals(accommodationData.getAccommodationByType(res.getTypeAccommodation()),res);
		}
		accommodationData.accommodationRemove(accommodation);
		accommodationData.accommodationRemove(accommodation2);
	}
}