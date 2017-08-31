package models.entities.data.test;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import settings.DatabaseServerTest;

@RunWith(Suite.class)
@SuiteClasses({ CustomerDataTest.class, 
	ReceptionistDataTest.class })
public class AllTests {
	
}
