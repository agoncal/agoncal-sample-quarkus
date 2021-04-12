package org.agoncal.sample.quarkus;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void shouldFindBookByIdWithoutOptional() {
        // Found
        given()
            .pathParam("id", 9000).
        when()
            .get("/api/books/without/{id}").
        then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("title", Is.is("Understanding Quarkus"))
            .body("author", Is.is("Antonio Goncalves"));

        // Not Found
        given()
            .pathParam("id", 9999).
        when()
            .get("/api/books/without/{id}").
        then()
            .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void shouldFindBookByIdWithOptional() {
        // Found
        given()
            .pathParam("id", 9000).
        when()
            .get("/api/books/with/{id}").
        then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("title", Is.is("Understanding Quarkus"))
            .body("author", Is.is("Antonio Goncalves"));

        // Not Found
        given()
            .pathParam("id", 9999).
        when()
            .get("/api/books/with/{id}").
        then()
            .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void shouldFindBookByIdWithResponse() {
        // Found
        given()
            .pathParam("id", 9000).
        when()
            .get("/api/books/response/{id}").
        then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("title", Is.is("Understanding Quarkus"))
            .body("author", Is.is("Antonio Goncalves"));

        // Not Found
        given()
            .pathParam("id", 9999).
        when()
            .get("/api/books/response/{id}").
        then()
            .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

}
