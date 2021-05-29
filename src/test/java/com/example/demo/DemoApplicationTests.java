package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@Disabled
@SpringBootTest
class DemoApplicationTests {

	@Disabled @Test
	public void should_return_simple_string_on_get_request() {
	when().
		get("/users/simple/{id}", 1).
	then()
		.statusCode(200)
		.body(equalTo("get user was called with userId = 1"));
	}

	//http://localhost:8080/users?page=1&limit=50&sort=bla-bla
	@Disabled @Test
	public void should_return_demo_string_using_annRequestParam(){
		when().
				get("/users?page={page}&limit={limit}&sort={sort}", 1, 50, "bla-bla").
		then().
				statusCode(200).
				body(equalTo("get users was called with page = 1 and limit = 50 and sort = bla-bla"));
	}

	@Disabled @Test
	public void should_return_JSON_object_data() {
		given().
				accept("application/json").
		when().
				get("/users/1").
		then().
				statusCode(200).
				//body(hasItems(4)).
				body("firstName", equalTo("Ivan")).
				body("secondName", equalTo("Ivanov"));
	}

	@Disabled @Test
	public void should_return_XML_object_data() {
		given().
				header("Content-Type", "application/xml").
		when().
				get("/users/1").
		then().
				statusCode(200).
				body("firstName", equalTo("Ivan")).
				body("secondName", equalTo("Ivanov"));
	}
}
