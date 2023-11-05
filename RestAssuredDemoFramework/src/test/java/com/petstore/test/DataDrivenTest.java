package com.petstore.test;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.petstore.endpoints.UserEndPoints;
import com.petstore.payload.User;
import com.petstore.utilities.DataProviders;

import io.restassured.response.Response;

public class DataDrivenTest {
  @Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
  public void postUser(Hashtable<String, String> data) {
	  User user = new User();
	  user.setId(Integer.parseInt(data.get("UserId")));
	  user.setUsername(data.get("UserName"));
	  user.setFirstName(data.get("FirstName"));
	  user.setLastName(data.get("LastName"));
	  user.setEmail(data.get("Email"));
	  user.setPassword(data.get("Password"));
	  user.setPhone(data.get("Phone"));
	  
	  Response response =UserEndPoints.createUser(user);
	  response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(), 200);
  }
  @Test(priority=2,dataProvider="UserNames", dataProviderClass=DataProviders.class)
  public void delUser(String userName) {
	 Response response= UserEndPoints.deleteUser(userName);
	 response.then().log().all();
	 Assert.assertEquals(response.getStatusCode(), 200);
  }
}
