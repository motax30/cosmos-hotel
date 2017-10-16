package controllers;

import com.google.gson.*;

import models.entities.Address;
import models.entities.Receptionist;
import models.entities.data.ReceptionistData;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class ReceptionistController {

    public ReceptionistController() {
        // GET - index - return all receptionists
        get("/receptionists/", (req, res) -> {
            ReceptionistData receptionistData = new ReceptionistData();

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.add("receptionists", new Gson().toJsonTree(receptionistData.findAll()));
            return jsonResponse;
        });

        // POST - new - create a receptionist
        post("/receptionists/", (req, res) -> {
            JSONObject requestParams = new JSONObject(req.body());
            JSONObject receptionistJsonObject = requestParams.getJSONObject("receptionist");
            Receptionist receptionist = new Gson().fromJson(receptionistJsonObject.toString(), Receptionist.class);

            ReceptionistData receptionistData = new ReceptionistData();
            receptionistData.create(receptionist);

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

            receptionistData.update(receptionist);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("receptionist", new JsonParser().parse(new Gson().toJson(receptionist)).getAsJsonObject());
            return jsonObject;
        });

        // DELETE - destroy - destroy a receptionist
        delete("/receptionists/:receptionist_id/", (req, res) -> {
            String id = req.params(":receptionist_id");

            ReceptionistData receptionistData = new ReceptionistData();
            Receptionist receptionist = receptionistData.findBy("id", id);

            receptionistData.delete(receptionist);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("receptionist", new JsonParser().parse(new Gson().toJson(receptionist)).getAsJsonObject());
            return jsonObject;
        });
    }
}