package com.shopperstack.orders;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class GetOrders extends BaseClass{
	@Test
	public void getOrdersTest() {
		//  /shoppers/{shopperId}/orders

		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.get("/shoppers/"+shopper_id+"/orders");
		r.then()
		.assertThat().statusCode(200)
		.log().all();
		
		String expSucMsg = "Success";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);

	}
}
