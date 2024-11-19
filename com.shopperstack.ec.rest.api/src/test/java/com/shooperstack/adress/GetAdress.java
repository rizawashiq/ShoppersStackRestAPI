package com.shooperstack.adress;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class GetAdress extends BaseClass{
	@Test
	public void getAdressTest() {
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.get("/shoppers/"+shopper_id+"/address/"+adress_id);
		r.then().log().all();
		String expSucMsg = "Success";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);
	}
}
