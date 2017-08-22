package models.entities.data.test;

import org.junit.Test;

import models.entities.User;
import models.entities.data.UserData;
import settings.DatabaseServerTest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;

public class UserDataTest {
	UserData userData = new UserData(DatabaseServerTest.getServer().openClient());
	
	@After public void deleteDatabase() {
        new File("database/main-test.odb").delete();
    }
	
    @Test public void testUserAdd() {
        User user = new User();
        user.setFirstName("Roberto");
        user.setLastName("das Torres");
        user.setEmail("roberto@torres.com.br");
        user.setUserName("roberto-das-torres");
        user.setPassword("rob123456");
        assertTrue("the userName should be created", userData.userAdd(user));
    }
    
    @Test public void testUserNotExists() {
        User user = new User();
        user.setFirstName("João");
        user.setLastName("das Neves");
        user.setEmail("joao@inverno.com.br");
        user.setUserName("joaodasneves");
        user.setPassword("neves12345");
        assertFalse("the userName should not be found", userData.userExists(user.getUserName()));
    }
    
    @Test public void testUserLogin() {
    		User user = new User();
        user.setFirstName("João");
        user.setLastName("das Neves");
        user.setEmail("joao@inverno.com.br");
        user.setUserName("joaologin");
        user.setPassword("neves");
        userData.userAdd(user);
	    assertTrue("this userName and password should be able to login", userData.userLogin("joaologin", "neves"));
	}
}
