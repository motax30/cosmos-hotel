package models.entities.data.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.db4o.ObjectSet;
import com.fasterxml.uuid.Generators;

import models.entities.Accommodation;
import models.entities.data.AccommodationData;
import models.enumerates.Scope;
import models.enumerates.AccommodationType;
import models.util.TableDayleValue;

public class AccommodationDataTest{
	private AccommodationData accommodationData = new AccommodationData();
	private Accommodation accommodation;
	private Accommodation accommodation2;
	
	@SuppressWarnings("static-access")
	@Before
	public void setUp() {
		accommodation = new Accommodation(
				Generators.timeBasedGenerator().generate().toString(),
				AccommodationType.DOUBLE,
				new TableDayleValue().table.get(AccommodationType.DOUBLE),
				2,LocalDateTime.now(),LocalDateTime.now());
		accommodation2 = new Accommodation(
					Generators.timeBasedGenerator().generate().toString(),
					AccommodationType.SINGLE,
					new TableDayleValue().table.get(AccommodationType.SINGLE),
					2,LocalDateTime.now(),LocalDateTime.now());
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
		AccommodationType originalType = accommodation.getType();
		int originalUpdateAt = accommodation.getUpdatedAt().getNano();
		accommodationData.update(accommodation);
		AccommodationType atualType = accommodation.getType();
		int atualUpdateAt = accommodation.getUpdatedAt().getNano();
		assertNotEquals(originalType,atualType);
		assertNotEquals(originalUpdateAt,atualUpdateAt);
	}
	
	@Test
	public void testRemoveAccommodation() {
		accommodationData.create(accommodation);
		assertTrue(accommodationData.delete(accommodation));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testRemoveAllAccommodation() {
		accommodationData.create(accommodation);
		accommodationData.create(accommodation2);
		accommodationData.deleteAll();getClass();
		List<Accommodation> res = accommodationData.findAll();
		assertTrue(res.isEmpty());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetAccommodations() {
		accommodation2.setId("2");
		accommodation.setType(AccommodationType.SINGLE);
		accommodationData.create(accommodation2);
		ObjectSet<Accommodation>accommodations = accommodationData.findAll();
		while(accommodations.hasNext()) {
			Accommodation tmpAcc = accommodations.next();
			assertTrue(tmpAcc.equals(accommodation) || tmpAcc.equals(accommodation2));
		}
		accommodationData.delete(accommodation2);
	}
	
//	@Test
//	public void testfindAllAccommodationsWithSimilarTypeAccommodation() {
//		accommodationData.create(accommodation);
//		accommodation2.setId("2");
//		@SuppressWarnings("static-access")
//        AccommodationTypeInformation accommodationTypeInformations2 = new AccommodationTypeInformation(AccommodationType.SIMPLES,
//				new TableDayleValue().table.get(AccommodationType.DUPLO), 2);
//		accommodation2.setAccommodationTypeInformations(accommodationTypeInformations2);
//		accommodationData.create(accommodation2);
//		ObjectSet<Accommodation>res = accommodationData.findAllBy("typeAccommodation", AccommodationType.DUPLO.toString());
//		while (res.hasNext()) {
//			Accommodation acc = res.next();
//			assertTrue("N�o foram retornadas as Accommodations cadastradas.",acc.equals(accommodation) || acc.equals(accommodation2));
//		}
//		accommodationData.delete(accommodation2);	
//	}
}