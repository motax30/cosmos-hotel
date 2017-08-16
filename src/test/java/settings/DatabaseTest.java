package settings;

import java.io.File;
import java.util.Arrays;

public class DatabaseTest {
	static {
		String project_path = System.getProperty("user.dir");
		Arrays.stream(new File(project_path + "/database-test").listFiles()).forEach(File::delete);
	}
}
