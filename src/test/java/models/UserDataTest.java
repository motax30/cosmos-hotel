package models;

import org.junit.Test;

import com.db4o.Db4oEmbedded;

import settings.DatabaseTest;

import static org.junit.Assert.*;

import org.junit.After;

public class UserDataTest extends DatabaseTest {
	UserData userData = new UserData(Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "database-test/users.data"));
	
	@After
    public void afterTest() {
        userData.getUsersData().close();
    }
	
    @Test public void testAddUser() {
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
        assertFalse("the userName should not exist", userData.userExists(user.getUserName()));
    }
    
    @Test public void testLogin() {

	    assertTrue("this userName should login", userData.userLogin("roberto-das-torres", "rob123456"));
	}
}
