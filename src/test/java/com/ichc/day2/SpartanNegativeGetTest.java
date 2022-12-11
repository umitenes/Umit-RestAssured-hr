package com.ichc.day2;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init(){
        baseURI="http://44.203.73.41:8000";
    }

    @Test
    public void test(){
        Response response= given().accept(ContentType.XML)
                .when()
                    .get("/api/spartans/10");
        assertEquals(406,response.statusCode());
        assertEquals("application/xml;charset=UTF-8",response.contentType());

        response.prettyPrint();

    }

}
