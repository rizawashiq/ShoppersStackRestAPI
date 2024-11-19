package com.shopperstack.systemTest;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopperstack.POJOClasses.LoginPOJO;
import com.shopperstack.baseClass.BaseClass;

import io.restassured.response.Response;

public class SystemTest extends BaseClass {
	




	@Test(priority = 0)
	public void login() {
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

	@Test(priority = 1)
	public void viewProd() {

		Response r = given()
				.spec(req)
				.when()
				.get("/products/alpha");
		r.then()
		.spec(resp)
		.assertThat().statusCode(200)
		.log().all();

		product_id = r.jsonPath().getString("data[26].productId");
		System.out.println(product_id);
		System.out.println(token);

	}

	@Test(priority = 2)
	public void addWishList() {
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.body("{\r\n"
						+ "  \"productId\": "+product_id+",\r\n"
						+ "  \"quantity\": 1\r\n"
						+ "}")
				.when()
				.post("/shoppers/"+shopper_id+"/wishlist");
		r.then()
		//		.spec(resp)
		//		.statusCode(200)
		.assertThat().statusCode(201)
		.log().all();

		String expSucMsg = "Created";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);

	}


	@Test(priority = 3)
	public void getFromWishList() {
		System.out.println(token);
		System.out.println("=============");

		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.get("/shoppers/"+shopper_id+"/wishlist");
		r.then()
		.assertThat().statusCode(200)
		.log().all();

		String expSucMsg = "Success";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);


	}

	@Test(priority = 4)
	public void deleteFromWishList()
	{
		///shoppers/{shopperId}/wishlist/{productId}
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.delete("/shoppers/"+shopper_id+"/wishlist/"+product_id);
		r.then()
		.assertThat().statusCode(204)
		.log().all();

	}

	@Test(priority = 5)
	public void addPRodToCart() {
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

	@Test(priority = 6)
	public void getPRodFromCart() {


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

	@Test(priority = 7)
	public void updatePRodFromCart() {
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

	//	@Test(priority = 8)
	//	public void delPRodFromCart() {
	//		Response r = given()
	//						.spec(req)
	//						.auth().oauth2(token)
	//					.when()
	//					.delete("/shoppers/"+shopper_id+"/carts/"+product_id);
	//		
	//				r.then()
	//					.assertThat().statusCode(200)
	//					.log().all();
	//				String expSucMsg = "Success";
	//				String actMsg = r.jsonPath().get("message"); 
	//				Assert.assertEquals(expSucMsg, actMsg);
	//				
	//	}

	@Test(priority = 8)
	public void addAdress() {
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.body("{\r\n"
						+ "  \"buildingInfo\": \"Zeeba Mansion\",\r\n"
						+ "  \"city\": \"Mangalore\",\r\n"
						+ "  \"country\": \"India\",\r\n"
						+ "  \"name\": \"Riza  Washiq\",\r\n"
						+ "  \"phone\": \"7259682537\",\r\n"
						+ "  \"pincode\": \"560010\",\r\n"
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

	@Test(priority = 9)
	public void getAdress() {
		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.get("/shoppers/"+shopper_id+"/address/"+adress_id);
		r.then()
		.assertThat().statusCode(200)
		.log().all();
		String expSucMsg = "Success";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);
	}
	
//	@Test(priority = 9)
//	public void getAllAdress() {
//		Response r = given()
//				.spec(req)
//				.auth().oauth2(t)
//				.when()
//				.get("/shoppers/"+sid+"/address");
//		r.then().log().all();
//		String expSucMsg = "Success";
//		String actMsg = r.jsonPath().get("message"); 
//		Assert.assertEquals(expSucMsg, actMsg);
//	}

//	@Test(priority = 9)
//	public void updateAdress() {
//		Response r = given()
//				.spec(req)
//				.auth().oauth2(token)
//				.when()
//				.get("/shoppers/"+shopper_id+"/address/"+adress_id);
//		r.then().log().all();
//		String expSucMsg = "Success";
//		String actMsg = r.jsonPath().get("message"); 
//		Assert.assertEquals(expSucMsg, actMsg);
//	}

	//	@Test(priority = 10)
	//	public void deleteAdress() {
	//		///shoppers/{shopperId}/address/{addressId}
	//
	//		Response r = given()
	//				.spec(req)
	//				.auth().oauth2(token)
	//				.when()
	//				.get("shoppers/"+shopper_id+"/address/"+adress_id);
	//		r.then().
	//		assertThat().statusCode(200).
	//		log().all();
	//		
	//		String expSucMsg = "Success";
	//		String actMsg = r.jsonPath().get("message"); 
	//		Assert.assertEquals(expSucMsg, actMsg);
	//	}

	@Test(priority = 10)
	public void postOrder() {
		// /shoppers/{shopperId}/orders
		String b = "{\r\n"
				+ "  \"address\": {\r\n"
				+ "    \"addressId\": "+adress_id+"\r\n"
				+ "  },\r\n"
				+ "  \"paymentMode\": \"COD\"\r\n"
				+ "}";

		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.body(b)
				.when()
				.post("/shoppers/"+shopper_id+"/orders");
		r.then()
		.assertThat().statusCode(201)
		.log().all();

		String expSucMsg = "Created";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);

		order_id = Integer.parseInt(r.jsonPath().getString("data.orderId"));

	}

	@Test(priority = 11)
	public void getOrders() {
		//  /shoppers/{shopperId}/orders

		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.get("/shoppers/"+shopper_id+"/orders");
		r.then()
		.assertThat().statusCode(200)
		.log().all();
		
		String expSucMsg = "Success";
		String actMsg = r.jsonPath().get("message"); 
		Assert.assertEquals(expSucMsg, actMsg);

	}
	
	@Test(priority = 12)
	public void getOrderInvoice() {
		//  /shoppers/{shopperId}/orders

		Response r = given()
				.spec(req)
				.auth().oauth2(token)
				.when()
				.get("/shoppers/"+shopper_id+"/orders/"+order_id+"/invoice");
		r.then()
		.assertThat().statusCode(200)
		.log().all();
	}
	
	@Test(priority = 13)
	public void addReview() {
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
	
	@Test(priority = 14)
	public void getReview() {
		
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
	
	@Test(priority = 15)
	public void updateReview() {
		
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
	
	@Test(priority = 16)
	public void deleteReview() {
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
