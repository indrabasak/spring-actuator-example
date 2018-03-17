package com.basaki.controller;

import com.basaki.Application;
import io.restassured.http.ContentType;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * {@code BookControllerFunctionalTests} represents functional tests for
 * {@code BookController}.
 * <p/>
 *
 * @author Indra Basak
 * @since 03/16/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookControllerFunctionalTests {

    @Value("${local.server.port}")
    private Integer port;

    @Test
    public void testRead() throws IOException {
        given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .get("/books/6")
                .then()
                .statusCode(200)
                .body("id", equalTo(6))
                .body("title",
                        equalTo("The Pope of Physics: Enrico Fermi and the Birth of the Atomic Age"))
                .body("author", equalTo("Gino Segrè and Bettina Hoerlin"));
    }

    @Test
    public void testPing() {
        given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .get("/ping")
                .then()
                .statusCode(200).equals("pong");
    }
}
