package com.shopperstack.wishlist;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class AddToWishList extends BaseClass {
	@Test
	public void addWishListTest() {
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.body("{\r\n"
						+ "  \"productId\": "+product_id+",\r\n"
						+ "  \"quantity\": 1\r\n"
						+ "}")
				.when()
				.post("/shoppers/"+shopper_id+"/wishlist");
		r.then()
		//		.spec(resp)
		//		.statusCode(200)
		.log().all();
		System.out.println(shopper_id);
		System.out.println(product_id);
		System.out.println(token);
		System.out.println("=============");

	}
}
