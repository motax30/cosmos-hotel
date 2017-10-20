package controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.entities.Receptionist;
import models.entities.data.ReceptionistData;
import models.enumerates.Scope;

public class ReceptionistController {

    @SuppressWarnings("unused")
	public ReceptionistController() {
        // GET - index - return all receptionists
        get("/receptionists/", (req, res) -> {
            ReceptionistData receptionistData = new ReceptionistData();

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.add("receptionists", new Gson().toJsonTree(receptionistData.findAll(Scope.TESTE.toString())));
            return jsonResponse;
        });

        // POST - new - create a receptionist
        post("/receptionists/", (req, res) -> {
            JSONObject requestParams = new JSONObject(req.body());
            JSONObject receptionistJsonObject = requestParams.getJSONObject("receptionist");
            Receptionist receptionist = new Gson().fromJson(receptionistJsonObject.toString(), Receptionist.class);

            ReceptionistData receptionistData = new ReceptionistData();
            receptionistData.create(receptionist,Scope.PRODUCAO.toString());

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("receptionist", new JsonParser().parse(new Gson().toJson(receptionist)).getAsJsonObject());
            return jsonObject;
        });
        options("/receptionists/", (req, res) -> "");

        // GET - show - show a receptionist
        get("/receptionists/:receptionist_id/", (req, res) -> {
            String id = req.params(":receptionist_id");

            ReceptionistData receptionistData = new ReceptionistData();
            Receptionist receptionist = receptionistData.findBy("id", id);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("receptionist", new JsonParser().parse(new Gson().toJson(receptionist)).getAsJsonObject());
            return jsonObject;
        });
        options("/receptionists/:receptionist_id/", (req, res) -> "");

        // PUT - edit - edit a receptionist
        put("/receptionists/:receptionist_id/", (req, res) -> {
            String id = req.params(":receptionist_id");
            JSONObject requestParams = new JSONObject(req.body());

            ReceptionistData receptionistData = new ReceptionistData();
            Receptionist receptionist = receptionistData.findBy("id", id);

            String receptionistName = requestParams.getJSONObject("receptionist").getString("name");
            String notes = requestParams.getJSONObject("receptionist").getString("notes");

            receptionistData.update(receptionist,Scope.PRODUCAO.toString());

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("receptionist", new JsonParser().parse(new Gson().toJson(receptionist)).getAsJsonObject());
            return jsonObject;
        });

        // DELETE - destroy - destroy a receptionist
        delete("/receptionists/:receptionist_id/", (req, res) -> {
            String id = req.params(":receptionist_id");

            ReceptionistData receptionistData = new ReceptionistData();
            Receptionist receptionist = receptionistData.findBy("id", id);

            receptionistData.delete(receptionist,Scope.PRODUCAO.toString());

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("receptionist", new JsonParser().parse(new Gson().toJson(receptionist)).getAsJsonObject());
            return jsonObject;
        });
    }
}