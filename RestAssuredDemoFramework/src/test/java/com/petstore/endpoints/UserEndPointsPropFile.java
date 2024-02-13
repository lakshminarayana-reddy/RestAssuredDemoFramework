package com.petstore.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import com.petstore.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsPropFile {
	static ResourceBundle getUrl() {
		ResourceBundle bundle = ResourceBundle.getBundle("routes"); //To Load the properties file.
		return bundle;
	}

	public static Response createUser(User payLoad) {
		String createUrl= getUrl().getString("createUserUrl");
		Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payLoad)
							.when().post(createUrl);
		return response;
	}
	
	public static Response getUser(String userName) {
		String getUrl = getUrl().getString("getUserUrl");
		Response response = given().pathParam("username",userName)
				.when().get(getUrl);
		return response;
	}
	
	public static Response updateUser(User payLoad, String userName) {
		String updateUrl =getUrl().getString("updateUserUrl");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username",userName).body(payLoad)
				.when().put(updateUrl);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		String delUrl = getUrl().getString("deleteUserUrl");
		Response response = given().pathParam("username", userName)
				.when().delete(delUrl);
		return response;
	}

}
