package controller;

import config.ItemModel;
import config.UpdatedUserModel;
import config.UserModel;
import config.setup;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class userController extends setup {

    public userController(Properties prop) {
        this.prop = prop;
    }

    public Response userRegistration(UserModel userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given().contentType("application/json")
                .body(userModel)
                .when().post("/auth/register")
                .then()
                .statusCode(201)
                .extract()
                .response();

    }
    public Response UserRegistrationWithexistingEmail(UserModel userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given().contentType("application/json")
                .body(userModel)
                .when().post("/auth/register")
                .then()
                .statusCode(400)
                .extract()
                .response();
    }


    public Response Adminlogin(UserModel userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given().contentType("application/json")
                .body(userModel)
                .when().post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response Userlist() throws IOException {

        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given()
                .header("Authorization", "Bearer " + prop.getProperty("adminToken"))
                .when().get("/user/users")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response searchUser(String userId) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given()
                .header("Authorization", "Bearer " + prop.getProperty("adminToken"))
                .when().get("/user/" + userId)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response updateUser(UpdatedUserModel updatedUserModel, String userId) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given().contentType("application/json")
                .body(updatedUserModel)
                .header("Authorization", "Bearer " + prop.getProperty("adminToken"))
                .when().put("/user/" + userId)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response userlogin(UserModel userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        System.out.println("Sending login request for email: " + userModel.getEmail());
        System.out.println("Password: " + userModel.getPassword());

        return given().contentType("application/json")
                .body(userModel)
                .when().post("/auth/login").then()
                .statusCode(200)
                .extract()
                .response();
    }
    public Response userLoginInvalid(UserModel userModel){
        RestAssured.baseURI = prop.getProperty("baseUrI");
        System.out.println("Sending login request for email: " + userModel.getEmail());
        System.out.println("Password: " + userModel.getPassword());

        return given().contentType("application/json")
                .body(userModel)
                .when().post("/auth/login").then()
                .statusCode(401)
                .extract()
                .response();
    }

    public Response Itemlist(String userToken) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given()
                .header("Authorization", "Bearer " + userToken)
                .when().get("/costs").then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response AddItem(ItemModel ItemModel, String userToken) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given().contentType("application/json")
                .body(ItemModel)
                .header("Authorization", "Bearer " + userToken)
                .when().post("/costs")
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

    public Response editItem(ItemModel ItemModel, String userId, String ItemId, String userToken) {
        RestAssured.baseURI = prop.getProperty("baseUrI");
        return given().contentType("application/json")
                .body(ItemModel)
                .header("Authorization", "Bearer " + userToken)
                .when().put("/costs/" + ItemId)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
    public Response deleteItem(String userToken,String itemId){
        RestAssured.baseURI=prop.getProperty("baseUrI");
        return given()
                .header("Authorization","Bearer "+userToken)
                .when().delete("/costs/"+itemId)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
    public Response deleteItemInvalid(String token,String itemId){
        RestAssured.baseURI=prop.getProperty("baseUrI");
        return given()
                .header("Authorization","Bearer "+token)
                .when().delete("/costs/"+itemId)
                .then()
                .statusCode(404)
                .extract()
                .response();
    }

}
