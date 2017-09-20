package controllers;

import com.db4o.ObjectSet;
import com.google.gson.*;

import models.entities.Address;
import models.entities.Customer;
import models.entities.data.CustomerData;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class CustomerController {

    public CustomerController() {
        // GET - index - return all customers
        get("/customers/", (req, res) -> {
            CustomerData customerData = new CustomerData();
            ObjectSet customers;

            if (req.queryParams("filter[email]") != null) {
                customers =  customerData.getCustomersByEmail(req.queryParams("filter[email]"));
            } else {
                customers = customerData.getCustomers();
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
            customerData.customerAdd(customer);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("customer", new JsonParser().parse(new Gson().toJson(customer)).getAsJsonObject());
            return jsonObject;
        });
        options("/customers/", (req, res) -> "");

        // GET - show - show a customer
        get("/customers/:customer_id/", (req, res) -> {
            String id = req.params(":customer_id");

            CustomerData customerData = new CustomerData();
            Customer customer = customerData.getCustomerById(id);

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
            Customer customer = customerData.getCustomerById(id);

            customerJson.setId(customer.getId());
            customerData.customerUpdate(customerJson);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("customer", new JsonParser().parse(new Gson().toJson(customerJson)).getAsJsonObject());
            return jsonObject;
        });

        // DELETE - destroy - destroy a customer
        delete("/customers/:customer_id/", (req, res) -> {
            String id = req.params(":customer_id");

            CustomerData customerData = new CustomerData();
            Customer customer = customerData.getCustomerById(id);

            customerData.customerRemove(customer);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("customer", new JsonParser().parse(new Gson().toJson(customer)).getAsJsonObject());
            return jsonObject;
        });
    }
}