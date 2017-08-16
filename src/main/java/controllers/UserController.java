package controllers;

import static spark.Spark.*;

public class UserController {

	public UserController() {
		get("/users", (req, res) -> "Users");
	}
}
