package com.ichc.day3;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestWithParameters {
    @BeforeAll
    public static void init(){
        baseURI="http://44.203.73.41:8000";
    }

    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test(){
        Response response=given().accept(ContentType.JSON)
                    .and().pathParam("id",5)
                .when()
                    .get("/api/spartans/{id}");


        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));

        response.prettyPrint();
    }

    @DisplayName("GEt to request /api/spartans/{id} Negative test")
    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",500)
                .when()
                .get("/api/spartans/{id}");


        assertEquals(404,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));
        response.prettyPrint();
    }
    @DisplayName("GET request to /spi/spartans/search with Query Params")
    @Test
    public void test2(){
        Response response=given().log().all().
                accept(ContentType.JSON)
                .and().queryParam("nameContains","e")
                .and().queryParam("gender","Female")
                .when()
                .get("api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
        response.prettyPrint();
    }
    @DisplayName("GET request to /spi/spartans/search with Query Params (MAP)")
    @Test
    public void test3(){
        Map<String,Object>queryMap=new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response=given().log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
        response.prettyPrint();
    }
}

