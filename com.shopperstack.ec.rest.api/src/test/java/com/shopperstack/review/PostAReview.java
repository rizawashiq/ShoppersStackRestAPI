package com.shopperstack.review;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class PostAReview  extends BaseClass{
	@Test
	public void addReviewTest() {
		String b = "{\r\n"
				+ " \r\n"
				+ "  \"description\": \"Best Watch\",\r\n"
				+ "  \"heading\": \"Watch is good\",\r\n"
				+ "  \"rating\": 3,\r\n"
				+ "  \"shopperId\": "+shopper_id+",\r\n"
				+ "  \"shopperName\": \"Trump\"\r\n"
				+ "}";
		
		Response r = given()
						.spec(req)
						.auth().oauth2(token)
						.body(b)
					.when()
						.post("/reviews?productId="+product_id);
			r.then()
			.assertThat().statusCode(200)
			.log().all();
					
			review_id = r.jsonPath().getInt("data.reviewId");
			String expSucMsg = "Success";
			String actMsg = r.jsonPath().get("message"); 
			Assert.assertEquals(expSucMsg, actMsg);
						
	}
}
