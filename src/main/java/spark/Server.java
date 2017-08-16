package spark;

import static spark.Spark.*;

import controllers.*;

public class Server {

	/*
	 * Spark Server Initialization
	 */
	public static void main(String[] args) {
		// Getting information for Heroku Environment
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 8080;
        }
        port(port);
        
        // Filter
        before((req, res) -> {
            String path = req.pathInfo();
            if (path.endsWith("/"))
                res.redirect(path.substring(0, path.length() - 1));
        });
        
        	// Controllers (routes)
        new UserController();
	}
}
