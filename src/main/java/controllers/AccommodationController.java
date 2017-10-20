package controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.entities.Accommodation;
import models.entities.AccommodationTypeInformations;
import models.entities.data.AccommodationData;
import models.enumerates.Scope;
public class AccommodationController {
	@SuppressWarnings("unused")
	public AccommodationController() {
		 // GET - index - return all accommodations
		get("/accommodations/",(req,res)->{
			AccommodationData accommodationData = new AccommodationData(Scope.PRODUCAO.toString());
			
			JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("accommodations", accommodationData.findAll(Scope.TESTE.toString()));
			return jsonResponse;
		}
		);
		
		// POST - new - create a accommodation
        post("/accommodations/", (req, res) -> {
            JSONObject requestParams = new JSONObject(req.body());
            JSONObject accommodationJsonObject = requestParams.getJSONObject("accommodation");

            Accommodation accommodation = new Gson().fromJson(accommodationJsonObject.toString(), Accommodation.class);

            AccommodationData customerData = new AccommodationData(Scope.PRODUCAO.toString());
            customerData.create(accommodation,Scope.PRODUCAO.toString());

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("accommodation", new JsonParser().parse(new Gson().toJson(accommodation)).getAsJsonObject());
            return jsonObject;
        });
        options("/accommodations/", (req, res) -> "");

        // GET - show - show a accommodation
        get("/accommodations/:accommodation_id/", (req, res) -> {
            String id = req.params(":accommodation_id");

            AccommodationData accommodationData = new AccommodationData(Scope.PRODUCAO.toString());
            Accommodation accommodation = accommodationData.findBy("id",id);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("accommodation", new JsonParser().parse(new Gson().toJson(accommodation)).getAsJsonObject());
            return jsonObject;
        });
        options("/accommodations/:accommodation_id/", (req, res) -> "");

        // PUT - edit - edit a accommodation
        put("/accommodations/:accommodation_id/", (req, res) -> {
            String id = req.params(":customer_id");
            JSONObject requestParams = new JSONObject(req.body());

            AccommodationData accommodationData = new AccommodationData(Scope.PRODUCAO.toString());
            Accommodation accommodation = accommodationData.findBy("id",id);

            String accommodationType = requestParams.getJSONObject("accommodation").getString("typeAccommodation");
            JSONArray typeInformations = requestParams.getJSONObject("accommodation").getJSONArray("accommodationTypeInformations");

            AccommodationTypeInformations accommodationTypeInformations = new AccommodationTypeInformations(String.valueOf(typeInformations.getString(1)),
            		String.valueOf(typeInformations.getString(2)),Double.valueOf(typeInformations.getString(3)),Integer.valueOf(typeInformations.getString(4)));
			
            accommodation.setAccommodationTypeInformations(accommodationTypeInformations );
            accommodationData.update(accommodation,Scope.PRODUCAO.toString());

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("accommodation", new JsonParser().parse(new Gson().toJson(accommodation)).getAsJsonObject());
            return jsonObject;
        });

        // DELETE - destroy - destroy a accommodation
        delete("/accommodations/:accommodation_id/", (req, res) -> {
            String id = req.params(":accommodation_id");

            AccommodationData accommodationData = new AccommodationData(Scope.PRODUCAO.toString());
            Accommodation accommodation = accommodationData.findBy("id", id);

            accommodationData.delete(accommodation,Scope.PRODUCAO.toString());

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("accommodation", new JsonParser().parse(new Gson().toJson(accommodation)).getAsJsonObject());
            return jsonObject;
        });
	}
}
