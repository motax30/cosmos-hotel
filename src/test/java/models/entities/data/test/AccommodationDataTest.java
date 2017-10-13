package models.entities.data.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.db4o.ObjectSet;

import models.entities.Accommodation;
import models.entities.AccommodationTypeInformations;
import models.entities.data.AccommodationData;
import models.enumerates.TypeAccommodation;
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
		accommodation.setId("1");
		accommodationTypeInformations = new AccommodationTypeInformations(120,3,accommodation.getId());
		accommodation.setAccommodationTypeInformations(accommodationTypeInformations);
		accommodation.setTypeAccommodation(TypeAccommodation.DUPLO.toString());
	}
	
	@After
	public void finishedTest() {
		accommodationData.delete(accommodation);
	}

	@Test
	public void testCreateAccommodation() {
		assertTrue(accommodationData.create(accommodation));
	}
	
	@Test
	public void testCreateAccommodationExists() {
		accommodationData.create(accommodation);
		assertFalse(accommodationData.create(accommodation));
	}
	
	@Test
	public void testUpdateAccommodation() {
		accommodationData.create(accommodation);
		String originalType = accommodation.getTypeAccommodation();
		int originalUpdateAt = accommodation.getUpdatedAt().getNano();
		accommodation.setTypeAccommodation(TypeAccommodation.SIMPLES.toString());
		accommodationData.update(accommodation);
		String atualType = accommodation.getTypeAccommodation();
		int atualUpdateAt = accommodation.getUpdatedAt().getNano();
		assertNotEquals(originalType,atualType);
		assertNotEquals(originalUpdateAt,atualUpdateAt);
	}
	
	@Test
	public void testRemoveAccommodation() {
		accommodationData.create(accommodation);
		assertTrue(accommodationData.delete(accommodation));
	}
	
	@Test
	public void testRemoveAllAccommodation() {
		accommodationData.create(accommodation);
		accommodation2.setId("2");
		accommodationTypeInformations2 = new AccommodationTypeInformations(150,2,accommodation2.getId());
		accommodation2.setAccommodationTypeInformations(accommodationTypeInformations2);
		accommodation2.setTypeAccommodation(TypeAccommodation.SIMPLES.toString());
		accommodationData.create(accommodation2);
		accommodationData.deleteAll();
		assertTrue(accommodationData.findAll().isEmpty());
	}
	
	@Test
	public void testGetAccommodations() {
		accommodation2.setId("2");
		accommodationTypeInformations2 = new AccommodationTypeInformations(150,2,accommodation2.getId());
		accommodation2.setAccommodationTypeInformations(accommodationTypeInformations2);
		accommodation2.setTypeAccommodation(TypeAccommodation.SIMPLES.toString());
		accommodationData.create(accommodation2);
		ObjectSet<Accommodation>accommodations = accommodationData.findAll();
		while(accommodations.hasNext()) {
			Accommodation tmpAcc = accommodations.next();
			assertTrue(tmpAcc.equals(accommodation) || tmpAcc.equals(accommodation2));
		}
		accommodationData.delete(accommodation2);
	}
	
	@Test
	public void testfindAllAccommodationsWithSimilarTypeAccommodation() {
		accommodationData.create(accommodation);
		accommodation2.setId("2");
		AccommodationTypeInformations accommodationTypeInformations2 = new AccommodationTypeInformations(150,2,accommodation2.getId());
		accommodation2.setAccommodationTypeInformations(accommodationTypeInformations2);
		accommodation2.setTypeAccommodation(TypeAccommodation.DUPLO.toString());
		accommodationData.create(accommodation2);
		ObjectSet<Accommodation>res = accommodationData.findAllBy("typeAccommodation", TypeAccommodation.DUPLO.toString());
		while (res.hasNext()) {
			Accommodation acc = res.next();
			assertTrue("Não foram retornadas as Accommodations cadastradas.",acc.equals(accommodation) || acc.equals(accommodation2));
		}
		accommodationData.delete(accommodation2);	
	}
}