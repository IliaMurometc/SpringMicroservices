//TODO Добавить какой-то код кавередж верификатор и добавить столько тестов что бы провверить все
package com.example.demo;

import com.example.demo.ui.controllers.UserControllers;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.UserRest;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.util.UUID;

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
						.appendField("password", "password_12_password").toJSONString())
		.when()
				.post("/users")
		.then()
				.statusCode(200)
				.log().all()
				.body("firstName", equalTo("Kolia"))
				.body("secondName", equalTo("Nikolaev"));
	}

	//TODO Can be investigated
	@Test
	public void should_receive_and_return_json_by_POST_request_BAD() {
		given()
				.contentType(ContentType.JSON)
				.body(new JSONObject()
						.appendField("firstName", "Kolia")
						.appendField("secondName", "Nikolaev")
						.appendField("email", "Kolia.Nikolaev@gmail.com")
						.appendField("password", "pa").toJSONString())
				.when()
				.post("/users")
				.then()
				.statusCode(400)
				.log().all();
	}

	//TODO it does not work. Write more code in the test to check updating!
	@Disabled
	@Test
	public void should_PUT__update_user() {
		String userId = createUUID();
		UserDetailsRequestModel usersDetails = new UserDetailsRequestModel(userId, "Kolia", "Nikolaev", "olia.Nikolaev@gmail.com");
		UserControllers controllers = new UserControllers();
		controllers.createUsers(usersDetails);

		given()
				.contentType(ContentType.JSON)
				.body(new JSONObject()
						.appendField("firstName", "Kolia_new")
						.appendField("secondName", "Nikolaev_new").toJSONString())
		.when()
				.post("entity/" + userId)
		.then()
				.statusCode(200)
				.log().all()
				.body("firstName", equalTo("Kolia_new"))
				.body("secondName", equalTo("Nikolaev_new"));;
	}

	private String createUUID() {
		return UUID.randomUUID().toString();
	}

	@Test
	public void should_handle_exception (){
		when()
				.get("/users/exception/1")
				.then()
				.statusCode(500)
				.log().all();
	}

}
