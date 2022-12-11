package com.ichc.day3;
import com.ichc.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanTestsWithPath extends SpartanTestBase {

    @DisplayName("GET one spartan with Path Medthod")
    @Test
    public void test(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/spartans/{id}");


        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        System.out.println("response.path(\"id\".toString()) = " + response.path("id") .toString());
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());
        System.out.println("response.path(\"gender\").toString() = " + response.path("gender").toString());
        System.out.println("response.path(\"phone\").toString() = " + response.path("phone").toString());

        int id=response.path("id");
        String name=response.path("name");
        String gender=response.path("gender");
        long phone=response.path("phone");

        System.out.println(id);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);

        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(331282093,phone);
    }

    @DisplayName("GET all spartan and navigate with path")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/");
        int firstId=response.path("id[0]");
        System.out.println(firstId);
        String name=response.path("name[0]");
        System.out.println(name);

        String lastFistName=response.path("name[-2]");
        System.out.println(lastFistName);

        List<String> names=response.path("name");
        System.out.println(names);
        for (String s : names) {
            System.out.println(s);
        }
    }
}
