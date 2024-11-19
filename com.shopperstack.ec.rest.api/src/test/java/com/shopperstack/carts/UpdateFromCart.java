package com.shopperstack.carts;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class UpdateFromCart extends BaseClass{
	@Test
	public void updatePRodFromCartTest() {
		JSONObject j = new JSONObject();
		j.put("productId",product_id);
		j.put("quantity", "4");
		
		
		Response r = given()
						.spec(req)
						.auth().oauth2(token)
						.body(j)
					.when()
		.put("/shoppers/"+shopper_id+"/carts/"+item_id);
				r.then()
					.assertThat().statusCode(200)
					.log().all();
				
				String expSucMsg = "Data Updated";
				String actMsg = r.jsonPath().get("message"); 
				Assert.assertEquals(expSucMsg, actMsg);
	}
}
