package com.shopperstack.orders;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class PostOrder extends BaseClass{
	@Test
	public void postOrderTest() {
		// /shoppers/{shopperId}/orders
		String b = "{\r\n"
				+ "  \"address\": {\r\n"
				+ "    \"addressId\": "+adress_id+"\r\n"
				+ "  },\r\n"
				+ "  \"paymentMode\": \"COD\"\r\n"
				+ "}";

		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.body(b)
				.when()
				.post("/shoppers/"+shopper_id+"/orders");
		r.then()
		.log().all();

		String expSucMsg = "Created";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);

		order_id = Integer.parseInt(r.jsonPath().getString("data.orderId"));

	}
}
