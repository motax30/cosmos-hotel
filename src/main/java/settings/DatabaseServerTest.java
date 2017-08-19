package settings;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;

public class DatabaseServerTest {
	private static ObjectServer server;

	private DatabaseServerTest() {}
	
	public synchronized static ObjectServer getServer() {
		if (server == null) {
			ServerConfiguration config = new DatabaseConfiguration().getConfiguration();
	        
			server = Db4oClientServer.openServer(config,
					 "database/main-test.odb", 0);
		}
		return server;
	}
	
}
