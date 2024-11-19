package com.shopperstack.carts;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class GetFromCart extends BaseClass{
	@Test
	public void getPRodFromCartTest() {
		
		
		Response r = given()
						.spec(req)
						.auth().oauth2(token)
					.when()
						.get("/shoppers/"+shopper_id+"/carts");
				r.then()
					.assertThat().statusCode(200)
					.log().all();
				
				String expSucMsg = "Success";
				String actMsg = r.jsonPath().get("message"); 
				Assert.assertEquals(expSucMsg, actMsg);
	}
}
