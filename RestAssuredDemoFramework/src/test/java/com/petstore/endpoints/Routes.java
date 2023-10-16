package com.petstore.endpoints;
// It consists of urls of the api requests.

public class Routes {
	//base url
	public static String baseUrl ="https://petstore.swagger.io/v2";
	
	//user module
	
	public static String createUserUrl =baseUrl+"/user";
	public static String getUserUrl =baseUrl+"/user/{username}";
	public static String updateUserUrl = baseUrl+"/user/{username}";
	public static String deleteUserUrl =baseUrl+"/user/{username}";
}
