package com.shopperstack.login;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.shopperstack.POJOClasses.LoginPOJO;
import com.shopperstack.baseClass.BaseClass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Login extends BaseClass {



	@Test
	public void loginTest() {
		LoginPOJO l = new LoginPOJO("mrizawashiq@gmail.com", "Riza@2020", "SHOPPER");

		Response r = given()
				.spec(req)
				.body(l)
				.when()
				.post("/users/login");
		r.then()
		.statusCode(200)
		.spec(resp)
		.log().all();

		String msg = r.jsonPath().getString("message");
		System.out.println(msg);

		BaseClass.shopper_id =Integer.parseInt(r.jsonPath().getString("data.userId"));
		token = r.jsonPath().getString("data.jwtToken");
		System.out.println(token);

	}

}
