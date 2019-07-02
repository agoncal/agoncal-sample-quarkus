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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BookResourceTest {

    private WebTarget webTarget;

    @Test
    public void shouldNotFindAnythingById() {
        given()
            .when().get("/api/books/9999999")
            .then()
            .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    public void shouldFindAll() {
        given()
            .when().get("/api/books")
            .then()
            .statusCode(OK.getStatusCode())
            .body("$", hasSize(1));
    }

    @Test
    public void shouldCreate() {
        final Book book = new Book("Joshua Bloch", "Effective Java (2nd Edition)", 2001, "Tech", " 978-0-3213-5668-0");
        String bookJson = JsonbBuilder.create().toJson(book);

        given().when().get("/api/books").then().body("$", hasSize(1));

        given()
            .body(bookJson)
            .contentType(MediaType.APPLICATION_JSON)
            .when()
            .post("/api/books")
            .then()
            .statusCode(CREATED.getStatusCode());

        given().when().get("/api/books").then().body("$", hasSize(2));
    }

    @Test
    @Disabled
    public void update() {
        final Book book = new Book("Joshua Bloch", "Effective Java (3rd Edition)", 2018, "Tech", " 978-0-1346-8599-1");
        final Response response = webTarget.path("{id}")
            .resolveTemplate("id", 1)
            .request()
            .put(entity(book, APPLICATION_JSON_TYPE));

        assertEquals(OK.getStatusCode(), response.getStatus());
    }

    @Test
    @Disabled
    public void delete() {
        final Response response = webTarget.path("{id}")
            .resolveTemplate("id", 1)
            .request()
            .delete();

        assertEquals(NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldNotDeleteAnythingById() {
        given()
            .when().get("/api/books/9999999")
            .then()
            .statusCode(NOT_FOUND.getStatusCode());
    }
}
