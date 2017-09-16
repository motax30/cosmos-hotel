package controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.entities.Accommodation;
import models.entities.AccommodationTypeInformations;
import models.entities.data.AccommodationData;
public class AccommodationController {
	public AccommodationController() {
		 // GET - index - return all accommodations
		get("/accommodations/",(req,res)->{
			AccommodationData accommodationData = new AccommodationData();
			
			JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("accommodations", accommodationData.getAccommodations());
			return jsonResponse;
		}
		);
		
		// POST - new - create a accommodation
        post("/accommodations/", (req, res) -> {
            JSONObject requestParams = new JSONObject(req.body());
            JSONObject accommodationJsonObject = requestParams.getJSONObject("accommodation");

            Accommodation accommodation = new Gson().fromJson(accommodationJsonObject.toString(), Accommodation.class);

            AccommodationData customerData = new AccommodationData();
            customerData.accommodationAdd(accommodation);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("accommodation", new JsonParser().parse(new Gson().toJson(accommodation)).getAsJsonObject());
            return jsonObject;
        });
        options("/accommodations/", (req, res) -> "");

        // GET - show - show a accommodation
        get("/accommodations/:accommodation_id/", (req, res) -> {
            String id = req.params(":accommodation_id");

            AccommodationData accommodationData = new AccommodationData();
            Accommodation accommodation = accommodationData.getAccommodationById(id);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("accommodation", new JsonParser().parse(new Gson().toJson(accommodation)).getAsJsonObject());
            return jsonObject;
        });
        options("/accommodations/:accommodation_id/", (req, res) -> "");

        // PUT - edit - edit a accommodation
        put("/accommodations/:accommodation_id/", (req, res) -> {
            String id = req.params(":customer_id");
            JSONObject requestParams = new JSONObject(req.body());

            AccommodationData accommodationData = new AccommodationData();
            Accommodation accommodation = accommodationData.getAccommodationById(id);

            String accommodationType = requestParams.getJSONObject("accommodation").getString("typeAccommodation");
            JSONArray typeInformations = requestParams.getJSONObject("accommodation").getJSONArray("accommodationTypeInformations");

            AccommodationTypeInformations accommodationTypeInformations = new AccommodationTypeInformations(
            		Double.valueOf(typeInformations.getString(1)),Integer.valueOf(typeInformations.getString(2)));
			
            accommodation.setAccommodationTypeInformations(accommodationTypeInformations );
			accommodation.setTypeAccommodation(accommodationType);
            accommodationData.accommodationUpdate(accommodation);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("accommodation", new JsonParser().parse(new Gson().toJson(accommodation)).getAsJsonObject());
            return jsonObject;
        });

        // DELETE - destroy - destroy a accommodation
        delete("/accommodations/:accommodation_id/", (req, res) -> {
            String id = req.params(":accommodation_id");

            AccommodationData accommodationData = new AccommodationData();
            Accommodation accommodation = accommodationData.getAccommodationById(id);

            accommodationData.accommodationRemove(accommodation);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("accommodation", new JsonParser().parse(new Gson().toJson(accommodation)).getAsJsonObject());
            return jsonObject;
        });

        /*
            Unrouted actions
         */
        // GET - return specific accommodation by typeAccommodation
        get("/accommodations/:typeAccommodation/", (req, res) -> {
            String typeAccommodation = req.params(":typeAccommodation");
            AccommodationData accommodationData = new AccommodationData();

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            Accommodation accommodation = accommodationData.getAccommodationByType(typeAccommodation);
            return accommodation == null ? "{}" : gson.toJson(accommodation);
        });
	}
}