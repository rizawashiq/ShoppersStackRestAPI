package com.shopperstack.review;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class UpdateTheReview extends BaseClass{
	@Test(priority = 15)
	public void updateReviewTest() {
		
		JSONObject j = new JSONObject();
		j.put("description", "Good Watch");
		j.put("heading", "Watch is good Virat");
		j.put("rating", "4");
		j.put("shopperId", shopper_id);
		j.put("shopperName", "Doland Trump");
		
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.body(j)
			.when()
				.put ("/reviews/"+review_id+"?productId="+product_id);
	r.then()
	.assertThat().statusCode(200)
	.log().all();
	String expSucMsg = "Success";
	String actMsg = r.jsonPath().get("message"); 
	Assert.assertEquals(expSucMsg, actMsg);
		
		
	}
}
