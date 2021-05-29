package com.example.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

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
}
