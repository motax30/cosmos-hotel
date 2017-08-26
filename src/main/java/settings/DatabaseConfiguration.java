package settings;

import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;

import models.entities.Receptionist;

public class DatabaseConfiguration {
	
	private static ServerConfiguration configuration;
	 
	public DatabaseConfiguration() {
		configuration= Db4oClientServer.newServerConfiguration();
		configuration.common().objectClass(Receptionist.class).objectField("userName").indexed(true);
	}
	
	public ServerConfiguration getConfiguration() {
		return configuration;
	}
}
