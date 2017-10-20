package controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import org.json.JSONObject;

import com.db4o.ObjectSet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.entities.Customer;
import models.entities.data.CustomerData;
import models.enumerates.Scope;

public class CustomerController {

    @SuppressWarnings("rawtypes")
	public CustomerController() {
        // GET - index - return all customers
        get("/customers/", (req, res) -> {
            CustomerData customerData = new CustomerData();
            ObjectSet customers;

            if (req.queryParams("filter[email]") != null) {
                customers = customerData.findAllBy("email", req.queryParams("filter[email]"));
            } else if (req.queryParams("filter[cpfNumber]") != null) {
                customers = customerData.findAllBy("cpfNumber", req.queryParams("filter[cpfNumber]"));
            } else {
                customers = customerData.findAll();
            }

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.add("customers", new Gson().toJsonTree(customers));
            return jsonResponse;
        });

        // POST - new - create a customer
        post("/customers/", (req, res) -> {
            JSONObject requestParams = new JSONObject(req.body());
            JSONObject customerJsonObject = requestParams.getJSONObject("customer");

            Customer customer = new Gson().fromJson(customerJsonObject.toString(), Customer.class);

            CustomerData customerData = new CustomerData();
            customerData.create(customer);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("customer", new JsonParser().parse(new Gson().toJson(customer)).getAsJsonObject());
            return jsonObject;
        });
        options("/customers/", (req, res) -> "");

        // GET - show - show a customer
        get("/customers/:customer_id/", (req, res) -> {
            String id = req.params(":customer_id");

            CustomerData customerData = new CustomerData();
            Customer customer = customerData.findBy("id", id);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("customer", new JsonParser().parse(new Gson().toJson(customer)).getAsJsonObject());
            return jsonObject;
        });
        options("/customers/:customer_id/", (req, res) -> "");

        // PUT - edit - edit a customer
        put("/customers/:customer_id/", (req, res) -> {
            JSONObject requestParams = new JSONObject(req.body());
            JSONObject customerJsonObject = requestParams.getJSONObject("customer");
            Customer customerJson = new Gson().fromJson(customerJsonObject.toString(), Customer.class);

            String id = req.params(":customer_id");
            CustomerData customerData = new CustomerData();
            Customer customer = customerData.findBy("id", id);

            customerJson.setId(customer.getId());
            customerData.update(customerJson);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("customer", new JsonParser().parse(new Gson().toJson(customerJson)).getAsJsonObject());
            return jsonObject;
        });

        // DELETE - destroy - destroy a customer
        delete("/customers/:customer_id/", (req, res) -> {
            String id = req.params(":customer_id");

            CustomerData customerData = new CustomerData();
            Customer customer = customerData.findBy("id", id);

            customerData.delete(customer);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("customer", new JsonParser().parse(new Gson().toJson(customer)).getAsJsonObject());
            return jsonObject;
        });
    }
}