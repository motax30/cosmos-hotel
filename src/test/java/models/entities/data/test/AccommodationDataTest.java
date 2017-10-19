package models.entities.data.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.db4o.ObjectSet;

import models.entities.Accommodation;
import models.entities.AccommodationTypeInformations;
import models.entities.data.AccommodationData;
import models.enumerates.Scope;
import models.enumerates.TypeAccommodation;
import models.util.TableDayleValue;

public class AccommodationDataTest{
	private AccommodationData accommodationData = new AccommodationData(Scope.TESTE.toString());
	private Accommodation accommodation;
	private Accommodation accommodation2;
	private AccommodationTypeInformations accommodationTypeInformations;
	private AccommodationTypeInformations accommodationTypeInformations2;
	
	
	@SuppressWarnings("static-access")
	@Before
	public void setUp() {
		accommodation = new Accommodation();
		accommodation2 = new Accommodation();
		accommodationTypeInformations = new AccommodationTypeInformations(TypeAccommodation.SIMPLES,new TableDayleValue().table.get(TypeAccommodation.SIMPLES), 1);
		accommodation.setAccommodationTypeInformations(accommodationTypeInformations);
	}

	@Test
	public void testCreateAccommodation() {
		assertTrue(accommodationData.create(accommodation, Scope.TESTE.toString()));
	}
	
	@Test
	public void testCreateAccommodationExists() {
		accommodationData.create(accommodation, Scope.TESTE.toString());
		assertFalse(accommodationData.create(accommodation, Scope.TESTE.toString()));
	}
	
	@Test
	public void testUpdateAccommodation() {
		accommodationData.create(accommodation, Scope.TESTE.toString());
		String originalType = accommodation.getAccommodationTypeInformations().getTypeAccommodation();
		int originalUpdateAt = accommodation.getUpdatedAt().getNano();
		accommodationData.update(accommodation, Scope.TESTE.toString());
		String atualType = accommodation.getAccommodationTypeInformations().getTypeAccommodation();
		int atualUpdateAt = accommodation.getUpdatedAt().getNano();
		assertNotEquals(originalType,atualType);
		assertNotEquals(originalUpdateAt,atualUpdateAt);
	}
	
	@Test
	public void testRemoveAccommodation() {
		accommodationData.create(accommodation, Scope.TESTE.toString());
		assertTrue(accommodationData.delete(accommodation, Scope.TESTE.toString()));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testRemoveAllAccommodation() {
		accommodationData.create(accommodation, Scope.TESTE.toString());
		accommodation2.setId("2");
		accommodationTypeInformations2 = new AccommodationTypeInformations(TypeAccommodation.SIMPLES,
				new TableDayleValue().table.get(TypeAccommodation.SIMPLES), 1);
		accommodation2.setAccommodationTypeInformations(accommodationTypeInformations2);
		accommodationData.create(accommodation2, Scope.TESTE.toString());
		accommodationData.deleteAll(Scope.TESTE.toString());getClass();
		List<Accommodation> res = accommodationData.findAll(Scope.TESTE.toString());
		assertTrue(res.isEmpty());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetAccommodations() {
		accommodation2.setId("2");
		accommodationTypeInformations2 = new AccommodationTypeInformations(TypeAccommodation.SIMPLES,
				new TableDayleValue().table.get(TypeAccommodation.DUPLO), 2);
		accommodation2.setAccommodationTypeInformations(accommodationTypeInformations2);
		accommodationData.create(accommodation2, Scope.TESTE.toString());
		ObjectSet<Accommodation>accommodations = accommodationData.findAll(Scope.TESTE.toString());
		while(accommodations.hasNext()) {
			Accommodation tmpAcc = accommodations.next();
			assertTrue(tmpAcc.equals(accommodation) || tmpAcc.equals(accommodation2));
		}
		accommodationData.delete(accommodation2, Scope.TESTE.toString());
	}
	
	@Test
	public void testfindAllAccommodationsWithSimilarTypeAccommodation() {
		accommodationData.create(accommodation, Scope.TESTE.toString());
		accommodation2.setId("2");
		@SuppressWarnings("static-access")
		AccommodationTypeInformations accommodationTypeInformations2 = new AccommodationTypeInformations(TypeAccommodation.SIMPLES,
				new TableDayleValue().table.get(TypeAccommodation.DUPLO), 2);
		accommodation2.setAccommodationTypeInformations(accommodationTypeInformations2);
		accommodationData.create(accommodation2, Scope.TESTE.toString());
		ObjectSet<Accommodation>res = accommodationData.findAllBy("typeAccommodation", TypeAccommodation.DUPLO.toString());
		while (res.hasNext()) {
			Accommodation acc = res.next();
			assertTrue("Não foram retornadas as Accommodations cadastradas.",acc.equals(accommodation) || acc.equals(accommodation2));
		}
		accommodationData.delete(accommodation2, Scope.TESTE.toString());	
	}
}