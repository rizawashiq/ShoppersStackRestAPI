package com.shopperstack.review;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class DeleteAReview extends BaseClass{
	@Test(priority = 16)
	public void deleteReviewTest() {
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
			.when()
				.delete("/reviews/"+review_id+"?productId="+product_id);
	r.then()
	.assertThat().statusCode(200)
	.log().all();
	String expSucMsg = "Success";
	String actMsg = r.jsonPath().get("message"); 
	Assert.assertEquals(expSucMsg, actMsg);
	}
}
