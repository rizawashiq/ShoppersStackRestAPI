package com.shopperstack.viewprod;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class ViewProd extends BaseClass {
	@Test
	public void viewProdTest() {
		
		Response r = given()
						.spec(req)
					.when()
						.get("/products/alpha");
		r.then()
			.spec(resp)
			.statusCode(200)
			.log().all();
		
		product_id = r.jsonPath().getString("data[26].productId");
		System.out.println(product_id);
		System.out.println(token);
					
	}
}
