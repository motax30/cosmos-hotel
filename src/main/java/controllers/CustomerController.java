package controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.entities.Customer;
import models.entities.data.CustomerData;

public class CustomerController {

    public CustomerController() {
        // GET - index - return all customers
        get("/customers/", (req, res) -> {
            CustomerData costumerData = new CustomerData();
            // TODO: make it from gson, as same from show route
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("customers", costumerData.getCustomers());

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
            String id = req.params(":customer_id");
            JSONObject requestParams = new JSONObject(req.body());

            CustomerData customerData = new CustomerData();
            Customer customer = customerData.getCustomerById(id);

            String customerName = requestParams.getJSONObject("customer").getString("name");
            String notes = requestParams.getJSONObject("customer").getString("notes");

            customer.setName(customerName);
            customer.setNotes(notes);

            customerData.customerUpdate(customer);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("customer", new JsonParser().parse(new Gson().toJson(customer)).getAsJsonObject());
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

        /*
            Unrouted actions
         */
        // GET - return specific customer by Name
        get("/customers/:name/", (req, res) -> {
            String customerName = req.params(":name");
            CustomerData customerData = new CustomerData();

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            Customer customer = customerData.getCustomerByCustomerName(customerName);
            return customer == null ? "{}" : gson.toJson(customer);
        });

        // GET - return specific customer by cpf
        get("/customers/:cpf/", (req, res) -> {
            String customerCpf = req.params(":cpf");
            CustomerData customerData = new CustomerData();

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            Customer customer = customerData.getCustomerByCustomerCpf(customerCpf);
            return customer == null ? "{}" : gson.toJson(customer);
        });
    }
}