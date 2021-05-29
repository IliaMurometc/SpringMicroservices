package com.example.demo;

import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@Disabled
@SpringBootTest
class DemoApplicationTests {

	@Test
	public void should_return_simple_string_on_get_request() {
	when()
		.get("/users/simple/{id}", 1)
	.then()
		.statusCode(200)
		.body(equalTo("get user was called with userId = 1"));
	}

	//http://localhost:8080/users?page=1&limit=50&sort=bla-bla
	@Test
	public void should_return_demo_string_using_annRequestParam(){
		when()
				.get("/users?page={page}&limit={limit}&sort={sort}", 1, 50, "bla-bla")
		.then()
				.statusCode(200)
				.body(equalTo("get users was called with page = 1 and limit = 50 and sort = bla-bla"));
	}

	@Test
	public void should_return_JSON_object_data() {
		given()
				.accept("application/json")
		.when()
				.get("/users/1")
		.then()
				.statusCode(200)
				.log().all()
				.body("firstName", equalTo("Ivan"))
				.body("secondName", equalTo("Ivanov"));
	}

	@Test
	public void should_return_XML_object_data() {
		given()
				.accept("application/xml")
		.when()
				.get("/users/1")
		.then()
				.statusCode(200)
				.log().all()
				.body("UserRest.firstName", equalTo("Ivan"))
				.body("UserRest.secondName", equalTo("Ivanov"));
	}

	@Test
	public void should_return_responseEntity_with_differ_statusCode_201() {
		when()
				.get("/users/entity/1")
		.then()
				.statusCode(201)
				.log().all()
				.body("firstName", equalTo("Petro"))
				.body("secondName", equalTo("Petrov"));
	}

	@Test
	public void should_receive_and_return_json_by_POST_request() {

		given()
				.contentType(ContentType.JSON)
				.body(new JSONObject()
						.appendField("firstName", "Kolia")
						.appendField("secondName", "Nikolaev")
						.appendField("email", "Kolia.Nikolaev@gmail.com")
						.appendField("password", "password_password").toJSONString())
		.when()
				.post("/users")
		.then()
				.statusCode(200)
				.log().all()
				.body("firstName", equalTo("Kolia"))
				.body("secondName", equalTo("Nikolaev"));
	}
}
