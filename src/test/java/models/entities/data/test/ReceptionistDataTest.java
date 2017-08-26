package models.entities.data.test;

import org.junit.Test;

import models.entities.Receptionist;
import models.entities.data.ReceptionistData;
import settings.DatabaseServerTest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;

public class ReceptionistDataTest {
	ReceptionistData receptionistData = new ReceptionistData(DatabaseServerTest.getServer().openClient());
	
	@After public void deleteDatabase() {
        new File("database/main-test.odb").delete();
    }
	
    @Test public void testReceptionistAdd() {
        Receptionist receptionist = new Receptionist();
        receptionist.setFirstName("Roberto");
        receptionist.setLastName("das Torres");
        receptionist.setEmail("roberto@torres.com.br");
        receptionist.setUserName("roberto-das-torres");
        receptionist.setPassword("rob123456");
        assertTrue("the receptionistName should be created", receptionistData.receptionistAdd(receptionist));
    }
    
    @Test public void testReceptionistNotExists() {
        Receptionist receptionist = new Receptionist();
        receptionist.setFirstName("João");
        receptionist.setLastName("das Neves");
        receptionist.setEmail("joao@inverno.com.br");
        receptionist.setUserName("joaodasneves");
        receptionist.setPassword("neves12345");
        assertFalse("the receptionistName should not be found", receptionistData.receptionistExists(receptionist.getUserName()));
    }
    
    @Test public void testReceptionistLogin() {
    		Receptionist receptionist = new Receptionist();
        receptionist.setFirstName("João");
        receptionist.setLastName("das Neves");
        receptionist.setEmail("joao@inverno.com.br");
        receptionist.setUserName("joaologin");
        receptionist.setPassword("neves");
        receptionistData.receptionistAdd(receptionist);
	    assertTrue("this receptionistName and password should be able to login", receptionistData.receptionistLogin("joaologin", "neves"));
	}
}
