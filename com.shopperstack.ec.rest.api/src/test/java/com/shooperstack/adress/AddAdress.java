package com.shooperstack.adress;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class AddAdress extends BaseClass{
	@Test
	public void addAdressTest() {
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.body("{\r\n"
						+ "  \"buildingInfo\": \"Zeeba Mansion\",\r\n"
						+ "  \"city\": \"Mangalore\",\r\n"
						+ "  \"country\": \"India\",\r\n"
						+ "  \"name\": \"Riza  Washiq\",\r\n"
						+ "  \"phone\": \"7259682537\",\r\n"
						+ "  \"pincode\": \"574219\",\r\n"
						+ "  \"state\": \"Karnataka\"\r\n"
						+ "}")
				.when()
				.post("/shoppers/"+shopper_id+"/address");

		r.then()
		.assertThat().statusCode(201)
		.log().all();
		adress_id = Integer.parseInt(r.jsonPath().getString("data.addressId"));

		String expSucMsg = "Created";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);

	}
}
