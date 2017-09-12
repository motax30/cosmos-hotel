package settings;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;

public class DatabaseServer {
	private static ObjectServer server;

	private DatabaseServer() {}
	
	public synchronized static ObjectServer getServer() {
		if (server == null) {
			ServerConfiguration config = new DatabaseConfiguration().getConfiguration();
	        
			server = Db4oClientServer.openServer(config,
					 "database/main.odb", 0);
		}
		return server;
	}
	
	
}
