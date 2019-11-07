/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.agoncal.sample.quarkus.book.rest;


import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.sample.quarkus.book.domain.Book;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.json.bind.JsonbBuilder;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookResourceTest {

    private static String bookId;

    @Test
    public void shouldNotFindAnythingById() {
        given()
            .when().get("/api/books/9999999")
            .then()
            .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    @Order(1)
    public void shouldFindAllBeforeCreate() {
        given()
            .when().get("/api/books")
            .then()
            .statusCode(OK.getStatusCode())
            .body("size()", is(1));
    }

    @Test
    @Order(2)
    public void shouldCreate() {
        final Book book = new Book("Joshua Bloch", "Effective Java (2nd Edition)", 2001, "Tech", " 978-0-3213-5668-0");
        String bookJson = JsonbBuilder.create().toJson(book);

        String location = given()
            .body(bookJson)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(ACCEPT, APPLICATION_JSON)
            .when()
            .post("/api/books")
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract().header("Location");
        assertTrue(location.contains("/api/books"));

        // Stores the id
        String[] segments = location.split("/");
        bookId = segments[segments.length - 1];
        assertNotNull(bookId);
    }

    @Test
    @Order(3)
    public void shouldFindAllAfterCreate() {
        given()
            .when().get("/api/books")
            .then()
            .statusCode(OK.getStatusCode())
            .body("size()", is(2));
    }

    @Test
    @Order(4)
    public void delete() {
        given()
            .pathParam("id", bookId)
            .when().delete("/api/books/{id}")
            .then()
            .statusCode(NO_CONTENT.getStatusCode());
    }

    @Test
    @Order(5)
    public void shouldFindAllAfterDelete() {
        given()
            .when().get("/api/books")
            .then()
            .statusCode(OK.getStatusCode())
            .body("size()", is(1));
    }
}
