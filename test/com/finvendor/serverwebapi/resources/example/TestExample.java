package com.finvendor.serverwebapi.resources.example;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class TestExample {
	final static String ROOT_URI = "http://localhost:8181/findallexample";

	@Test
	public void simple_get_test() {
		Response response = get(ROOT_URI + "/list");
		System.out.println(response.asString());

		response.then().body("id", hasItems(1, 2));
		response.then().body("name", hasItems("Pankaj"));
	}
}
