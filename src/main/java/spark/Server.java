package spark;

import static spark.Spark.before;
import static spark.Spark.path;
import static spark.Spark.port;

import controllers.CustomerController;
import controllers.IndexController;
import controllers.LoginController;
import controllers.ReceptionistController;
import models.entities.Customer;
import models.entities.Receptionist;
import models.entities.data.CustomerData;
import models.entities.data.ReceptionistData;

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

        /* Filter for trailing slash */
        before((req, res) -> {
            String path = req.pathInfo();
            if (!path.endsWith("/"))
                res.redirect(path + "/");
        });

        /* Filter for CORS */
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Methods", "*");
            response.header("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Accept");
            response.header("Access-Control-Allow-Credentials", "true");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });

//        Uncomment below to init seed to login
        Receptionist receptionist = new Receptionist();
        receptionist.setUserName("admin");
        receptionist.setPassword("admin");
        ReceptionistData receptionistData = new ReceptionistData();
        receptionistData.receptionistAdd(receptionist);

        // Controllers (routes)
        path("/api", () -> {
            new IndexController();
            new ReceptionistController();
            new CustomerController();
            new LoginController();
        });
    }
}
