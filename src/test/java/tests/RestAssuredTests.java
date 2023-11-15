package tests;

import models.CheckNameOfNewUserModel;
import models.SetUserNewNameUsingPutModel;
import models.setUserNewNameUsingPatchModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTests {


        @Test
        void checkUsersPageStatus() {
            given()
                    .log().all()
                    .when()
                    .get("https://reqres.in/api/users?page=2")
                    .then()
                    .log().all()
                    .statusCode(200);
        }

        @Test
        void checkNameOfNewUser() {
            CheckNameOfNewUserModel requestBody = new CheckNameOfNewUserModel();
            requestBody.setName("morpheus");
            requestBody.setJob("leader");

            given()
                    .log().all()
                    .contentType(JSON)
                    .body(requestBody)
                    .when()
                    .post("https://reqres.in/api/users")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(201)
                    .body("name", equalTo("morpheus"));
        }

        @Test
        void getUser() {
            given()
                    .log().all()
                    .when()
                    .get("https://reqres.in/api/users/2")
                    .then()
                    .log().all()
                    .statusCode(200);
        }

        @Test
        void setUserNewNameUsingPut() {
            SetUserNewNameUsingPutModel requestBodyUserNamePut = new SetUserNewNameUsingPutModel();
            requestBodyUserNamePut.setName("john");
            requestBodyUserNamePut.setJob("leader");

            given()
                    .log().all()
                    .contentType(JSON)
                    .body(requestBodyUserNamePut)
                    .when()
                    .put("https://reqres.in/api/users/2")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("name", equalTo("john"));
        }

        @Test
        void setUserNewNameUsingPatch() {
            setUserNewNameUsingPatchModel requestBodyForUserNameUsingPatch = new setUserNewNameUsingPatchModel();
            requestBodyForUserNameUsingPatch.setName("michael");
            requestBodyForUserNameUsingPatch.setJob("engineer");

            given()
                    .log().all()
                    .contentType(JSON)
                    .body(requestBodyForUserNameUsingPatch)
                    .when()
                    .patch("https://reqres.in/api/users/2")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("name", equalTo("michael"));
        }
    }
