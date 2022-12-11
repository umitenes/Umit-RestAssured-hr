package com.ichc.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {
    @BeforeAll
    public static void init(){
        baseURI="http://44.203.73.41:1000/ords/hr";
    }

    @DisplayName("GET request to /regions")
    @Test
    public void test(){
        Response response=get("/regions");
        System.out.println("response.statusCode() = " + response.statusCode());
    }

    @DisplayName("GET request to /regions/s")
    @Test
    public void test1(){
        Response response= given().accept(ContentType.JSON)
                .when()
                    .get("/regions/2");


        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Americas"));
        response.prettyPrint();
    }




}
