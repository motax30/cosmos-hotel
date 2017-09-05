package models.entities.data.test;

import org.junit.Test;

import models.entities.Receptionist;
import models.entities.data.ReceptionistData;
import settings.DatabaseServerTest;

import static org.junit.Assert.*;

public class ReceptionistDataTest {
	private ReceptionistData receptionistData = new ReceptionistData(DatabaseServerTest.getServer().openClient());
	
    @Test public void testReceptionistAdd() {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("Roberto das Torres");
        receptionist.setEmail("roberto@torres.com.br");
        receptionist.setUserName("roberto-das-torres");
        receptionist.setPassword("rob123456");
        assertTrue("the receptionist should be created", receptionistData.receptionistAdd(receptionist));
        receptionistData.receptionistRemove(receptionist);
    }
    
    @Test public void testReceptionistRemove() {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("João das Torres");
        receptionist.setEmail("joao@torres.com.br");
        receptionist.setUserName("joao-das-torres");
        receptionist.setPassword("jdab123456");
        receptionistData.receptionistAdd(receptionist);
        assertTrue("Recepcionist should be removed",receptionistData.receptionistRemove(receptionist));
    }
    
    @Test public void testReceptionistNotExists() {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("João das Neves");
        receptionist.setEmail("joao@inverno.com.br");
        receptionist.setUserName("joaodasneves");
        receptionist.setPassword("neves12345");
        assertFalse("the receptionistName should not be found", receptionistData.receptionistExists(receptionist.getUserName()));
    }
    
    @Test public void testReceptionistLogin() {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("João das Neves");
        receptionist.setEmail("joao@inverno.com.br");
        receptionist.setUserName("joaologin");
        receptionist.setPassword("neves");
        receptionistData.receptionistAdd(receptionist);
	    assertTrue("this receptionistName and password should be able to login", receptionistData.receptionistLogin("joaologin", "neves"));
	}
}