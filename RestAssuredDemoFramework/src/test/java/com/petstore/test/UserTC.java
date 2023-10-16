package com.petstore.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.petstore.endpoints.UserEndPoints;
import com.petstore.payload.User;

import io.restassured.response.Response;

public class UserTC {
	Faker faker;
	User user;
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		user = new User();
		
		user.setId(faker.idNumber().hashCode());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		user.setPassword(faker.internet().password(5,10));
		user.setPhone(faker.phoneNumber().cellPhone());
	}
	@Test
	public void testCreateUser() {
		Response response =UserEndPoints.createUser(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
