package controllers;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.User;
import models.UserData;

public class UserController {
	public UserController() {
		// GET - return all users
		get("/users/", (req, res) -> {
			UserData userData = new UserData();
			
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			return gson.toJson(userData.getUsers());
		});
		
		// GET - return specific user by userName
		get("/users/:user_name/", (req, res) -> {
			String userName = req.params(":user_name");
			UserData userData = new UserData();
		
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			User user = userData.getUserByUserName(userName);
			return user == null ? "{}" : gson.toJson(user);
		});
		
	}
}
