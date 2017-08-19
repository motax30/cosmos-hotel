package spark;

import static spark.Spark.before;
import static spark.Spark.port;

import controllers.IndexController;
import controllers.LoginController;
import controllers.UserController;

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
            port = 3000;
        }
        port(port);
        
        // Filter
        before((req, res) -> {
            String path = req.pathInfo();
            
            if (!path.endsWith("/"))
                res.redirect(path + "/");
        });
        
        	// Controllers (routes)
        new IndexController();
        new UserController();
        new LoginController();
		
	}
}
