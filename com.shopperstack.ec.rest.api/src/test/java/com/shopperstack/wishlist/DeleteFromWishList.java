package com.shopperstack.wishlist;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class DeleteFromWishList extends BaseClass {
	@Test(priority = 4)
	public void deleteFromWishListTest()
	{
		///shoppers/{shopperId}/wishlist/{productId}
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.delete("/shoppers/"+shopper_id+"/wishlist/"+product_id);
		r.then()
		.statusCode(204)
		.log().all();
		
	}
	
}
