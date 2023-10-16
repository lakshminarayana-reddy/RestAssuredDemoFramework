package com.petstore.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.petstore.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(User payLoad) {
		Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payLoad)
							.when().post(Routes.createUserUrl);
		return response;
	}
	
	public static Response getUser(String userName) {
		Response response = given().pathParam("username",userName)
				.when().get(Routes.getUserUrl);
		return response;
	}
	
	public static Response updateUser(User payLoad, String userName) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username",userName).body(payLoad)
				.when().put(Routes.updateUserUrl);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		Response response = given().pathParam("username", userName)
				.when().delete(Routes.deleteUserUrl);
		return response;
	}

}
