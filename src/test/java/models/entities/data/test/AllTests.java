package models.entities.data.test;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import settings.DatabaseServerTest;

@RunWith(Suite.class)
@SuiteClasses({ CustomerDataTest.class, 
	ReceptionistDataTest.class, AccommodationDataTest.class,BookingDataTest.class })
public class AllTests {
	@BeforeClass
	public static void beforeClass() throws IOException {
		DatabaseServerTest.deleteTestDatabase();
	}
}
