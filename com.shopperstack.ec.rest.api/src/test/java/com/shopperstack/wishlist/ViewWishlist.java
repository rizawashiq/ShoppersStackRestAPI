package com.shopperstack.wishlist;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class ViewWishlist extends BaseClass {
	
	@Test
	public void getFromWishListTest() {
		System.out.println(token);
		System.out.println("=============");

		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.get("/shoppers/"+shopper_id+"/wishlist");
		r.then()
		.log().all();


	}

}
