package com.redhat.consulting;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when()
                .get("/hello")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAuthHelloEndpoint() {
        given()
                .when()
                .auth().basic("test","test")
                .get("/hello/auth")
                .then()
                .statusCode(200);
    }


    @Test
    public void testAuthFailingHelloEndpoint() {
        given()
                .when()
                .get("/hello/auth")
                .then()
                .statusCode(anyOf(is(401), is(403)));
    }
}