package controllers;

import static spark.Spark.options;
import static spark.Spark.post;

import java.util.HashMap;

import org.json.JSONObject;

import models.entities.Phone;
import models.entities.data.UserData;
import settings.JWTKey;

public class CustomerController {

	public CustomerController() {
		post("/auth/", (req, res) -> {
			HashMap<String, Object> claims = new HashMap<String, Object>();
			JSONObject jsonResponse = new JSONObject();
			
			if (!req.body().isEmpty()) {
				JSONObject requestParams = new JSONObject(req.body());
				if (requestParams.length() >= 2) {
					String name = requestParams.getString("name");

					UserData userData = new UserData();
					if (userData.userLogin(name, null)) {
						claims.put("username", name);
						String token = JWTKey.getToken(claims);
						jsonResponse.put("token", token);
					}
				}
			}
			return jsonResponse;
		});
	}
}
