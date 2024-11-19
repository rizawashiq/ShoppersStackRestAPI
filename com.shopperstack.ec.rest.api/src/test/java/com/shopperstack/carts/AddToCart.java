package com.shopperstack.carts;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class AddToCart extends BaseClass {
	@Test
	public void addPRodToCartTest() {
		JSONObject j = new JSONObject();
		j.put("productId",product_id);
		j.put("quantity", "2");
		
		
		Response r = given()
						.spec(req)
						.auth().oauth2(token)
						.body(j)
					.when()
						.post("/shoppers/"+shopper_id+"/carts");
				r.then()
					.assertThat().statusCode(201)
					.log().all();
		item_id = Integer.parseInt(r.jsonPath().getString("data.itemId"));
		String expSucMsg = "Created";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);
		
		
	}
}
