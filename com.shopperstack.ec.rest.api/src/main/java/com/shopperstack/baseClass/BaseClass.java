package com.shopperstack.baseClass;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClass {
//	public static int user_id ;
	public static int shopper_id;
	public static String product_id ;
	public static String token ;
	public static int item_id;
	public static int adress_id , order_id , review_id;
	
	public static RequestSpecification req;
	public static ResponseSpecification resp;
	
	@BeforeSuite
	public void bf() {
		RequestSpecBuilder reqspec = new RequestSpecBuilder();
		reqspec.setBaseUri("https://www.shoppersstack.com/shopping");
		reqspec.setContentType(ContentType.JSON);
//		reqspec.setAuth(RestAssured.oauth2(token));
//		reqspec.
		 req = reqspec.build();
		 
		 ResponseSpecBuilder respspec = new ResponseSpecBuilder();
		 respspec.expectContentType(ContentType.JSON);
		 resp = respspec.build();
		 
		
	}
}
