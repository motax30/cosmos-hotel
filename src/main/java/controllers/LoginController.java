package controllers;

import static spark.Spark.*;

import models.entities.data.UserData;

public class LoginController {
	public LoginController() {
		post("/login/:userName/:password/", (req, res) -> {
			String userName = req.params("userName");
			String password = req.params("password");
			UserData userData = new UserData();
			if (userData.userLogin(userName, password)) {
				req.session().attribute("currentUser", userName);
				return "Login successful";
			}
			return "{}";
		});
		
		// GET - return specific user by userName
		delete("/logout/", (req, res) -> {
			return "";
		});
	}
}
