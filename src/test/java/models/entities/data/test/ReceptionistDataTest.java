package models.entities.data.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import models.entities.Receptionist;
import models.entities.data.ReceptionistData;
import models.enumerates.Scope;

public class ReceptionistDataTest {
	private ReceptionistData receptionistData = new ReceptionistData(Scope.PRODUCAO.toString());
	
    @Test public void testReceptionistCreate() {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("adriano");
        receptionist.setEmail("adriano@mota.com.br");
        receptionist.setUserName("motax30");
        receptionist.setPassword("123456");
        assertTrue("the receptionist should be created", receptionistData.create(receptionist,Scope.TESTE.toString()));
        receptionistData.delete(receptionist,Scope.TESTE.toString());
    }
    
    @Test public void testReceptionistDelete() {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("João das Torres");
        receptionist.setEmail("joao@torres.com.br");
        receptionist.setUserName("joao-das-torres");
        receptionist.setPassword("jdab123456");
        receptionistData.create(receptionist,Scope.TESTE.toString());
        assertTrue("Recepcionist should be removed",receptionistData.delete(receptionist,Scope.TESTE.toString()));
    }
    
    @Test public void testReceptionistNotExists() {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("João das Neves");
        receptionist.setEmail("joao@inverno.com.br");
        receptionist.setUserName("joaodasneves");
        receptionist.setPassword("neves12345");
        assertFalse("the receptionistName should not be found", receptionistData.exists("userName",receptionist.getUserName()));
    }
    
    @Test public void testReceptionistLogin() {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("João das Neves");
        receptionist.setEmail("joao@inverno.com.br");
        receptionist.setUserName("joaologin");
        receptionist.setPassword("neves");
        receptionistData.create(receptionist,Scope.TESTE.toString());
	    assertTrue("this receptionistName and password should be able to login", receptionistData.receptionistLogin("joaologin", "neves"));
	}
}