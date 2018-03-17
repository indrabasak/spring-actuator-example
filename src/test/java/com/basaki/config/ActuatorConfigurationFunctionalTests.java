package com.basaki.config;

import com.basaki.Application;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * {@code ActuatorConfigurationFunctionalTests} represents functional tests for
 * {@code ActuatorConfiguration}.
 * <p/>
 *
 * @author Indra Basak
 * @since 03/16/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ActuatorConfigurationFunctionalTests {

    @Value("${local.server.port}")
    private Integer port;

    @Autowired
    ApplicationContext context;

    @Test
    public void testMyHealth() {
        given()
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .get("/myhealth")
                .then()
                .statusCode(200)
                .body("MyStatus", equalTo("is happy"));
    }
}
