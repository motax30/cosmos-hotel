package spark;

import static spark.Spark.before;
import static spark.Spark.port;
import static spark.Spark.path;

import controllers.IndexController;
import controllers.LoginController;
import controllers.ReceptionistController;

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
        
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "GET,PUT,POST,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
            response.header("Access-Control-Allow-Credentials", "true");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
        
        	// Controllers (routes)
        path("/api", () -> {
        		new IndexController();
            new ReceptionistController();
            new LoginController();
        });
		
	}
	
}
