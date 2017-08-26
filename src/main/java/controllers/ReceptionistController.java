package controllers;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.entities.Receptionist;
import models.entities.data.ReceptionistData;

public class ReceptionistController {
	public ReceptionistController() {
		// GET - return all users
		get("/users/", (req, res) -> {
			ReceptionistData userData = new ReceptionistData();
			
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			return gson.toJson(userData.getReceptionists());
		});
		
		// GET - return specific user by userName
		get("/users/:user_name/", (req, res) -> {
			String userName = req.params(":user_name");
			ReceptionistData userData = new ReceptionistData();
		
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			Receptionist user = userData.getReceptionistByUserName(userName);
			return user == null ? "{}" : gson.toJson(user);
		});
		
	}
}
