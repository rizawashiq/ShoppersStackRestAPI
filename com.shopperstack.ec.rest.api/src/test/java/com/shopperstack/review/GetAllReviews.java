package com.shopperstack.review;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class GetAllReviews extends BaseClass{
	@Test
	public void getReviewTest() {
		
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
			.when()
				.get("/reviews/"+product_id);
	r.then()
	.assertThat().statusCode(200)
	.log().all();
	
	String expSucMsg = "Success";
	String actMsg = r.jsonPath().get("message"); 
	Assert.assertEquals(expSucMsg, actMsg);
	}
}
