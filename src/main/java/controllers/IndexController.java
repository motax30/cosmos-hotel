package controllers;

import static spark.Spark.get;

public class IndexController {
	public IndexController() {
		get("/", (req, res) -> "API RESTful Cosmos Hotel");
	}
}
