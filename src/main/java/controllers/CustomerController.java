package controllers;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.entities.Customer;
import models.entities.data.CustomerData;

public class CustomerController {

	public CustomerController() {
		// GET - return all customers
				get("/customers/", (req, res) -> {
					CustomerData customerData = new CustomerData();
					
					GsonBuilder builder = new GsonBuilder();
					Gson gson = builder.create();
					
					return gson.toJson(customerData.getCustomers());
				});
				
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