package com.petstore.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.petstore.endpoints.UserEndPointsPropFile;
import com.petstore.payload.User;

import io.restassured.response.Response;

public class UserTCPropFile {
	Faker faker;
	User user;
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		user = new User();
		
		user.setId(faker.idNumber().hashCode());
		user.setUsername(faker.name().username());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		user.setPassword(faker.internet().password(5,10));
		user.setPhone(faker.phoneNumber().cellPhone());
	}
	@Test(priority=1)
	public void testCreateUser() {
		Response response =UserEndPointsPropFile.createUser(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(user.getUsername());
	}
	@Test(priority=2)
	public void testGetUser() {
		Response response = UserEndPointsPropFile.getUser(user.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(user.getUsername());
	}
	@Test(priority=3)
	public void testUpdateUser() {
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPointsPropFile.updateUser(user, user.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(user.getUsername());
	}
	@Test(priority=4)
	public void testDeleteUser() {
		Response response = UserEndPointsPropFile.deleteUser(user.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(user.getUsername());
	}
}
