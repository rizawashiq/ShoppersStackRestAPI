package com.shopperstack.orders;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class GetInvoice extends BaseClass{
	@Test
	public void getOrderInvoiceTest() {
		//  /shoppers/{shopperId}/orders

		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.get("/shoppers/"+shopper_id+"/orders/"+order_id+"/invoice");
		r.then()
		.assertThat().statusCode(200)
		.log().all();

	}
}
