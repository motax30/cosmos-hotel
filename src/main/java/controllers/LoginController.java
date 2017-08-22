package controllers;

import static spark.Spark.options;
import static spark.Spark.post;

import java.util.HashMap;

import org.json.JSONObject;

import models.entities.data.UserData;
import settings.JWTKey;

public class LoginController {
	public LoginController() {
		post("/auth/", (req, res) -> {
			HashMap<String, Object> claims = new HashMap<String, Object>();
			JSONObject jsonResponse = new JSONObject();
			
			if (!req.body().isEmpty()) {
				JSONObject requestParams = new JSONObject(req.body());
				if (requestParams.length() >= 2) {
					String userName = requestParams.getString("username");
					String password = requestParams.getString("password");

					UserData userData = new UserData();
					if (userData.userLogin(userName, password)) {
						claims.put("username", userName);
						String token = JWTKey.getToken(claims);
						jsonResponse.put("token", token);
					}
				}
			}
			return jsonResponse;
		});

		options("/auth/", (req, res) -> {
			return "";
		});
		
		post("/auth/token-refresh/", (req, res) -> {
			JSONObject requestParams = new JSONObject(req.body());
			String token = requestParams.getString("token");
			
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("token", JWTKey.refreshToken(token));
			return jsonResponse;
		});
		
		options("/auth/token-refresh/", (req, res) -> {
			return "";
		});
	}
}
