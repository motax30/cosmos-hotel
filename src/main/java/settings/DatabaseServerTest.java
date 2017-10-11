package settings;

import java.io.File;
import java.io.IOException;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;
import com.db4o.foundation.io.File4;

public class DatabaseServerTest {
	private static ObjectServer server;

	private DatabaseServerTest() {}
	
	public synchronized static ObjectServer getServer() {
		if (server == null) {
			ServerConfiguration config = new DatabaseConfiguration().getConfiguration();
	        
			server = Db4oClientServer.openServer(config, "database/main-test.odb", 0);
		}
		return server;
	}

	public static void deleteTestDatabase() throws IOException {
        String p = new File("database").getAbsolutePath();
        File[] f = new File(p).listFiles();
        for (File file : f) {
        	file.setWritable(true);
			if(file.exists()?file.delete():false);
		}
	}
	
}
