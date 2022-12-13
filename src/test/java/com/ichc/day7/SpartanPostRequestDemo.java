package com.ichc.day7;

import com.ichc.pojo.Spartan;
import com.ichc.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SpartanPostRequestDemo extends SpartanTestBase {
    @DisplayName("something")
    @Test
    public void test(){
        String requestJsonBody="{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Hasan\",\n" +
                "  \"phone\": 1234567890\n" +
                "}";
        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage="A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Hasan"));
        assertThat(response.path("data.gender"),is("Male"));
    }
    @DisplayName("POST with Map to JSON")
    @Test
    public void test1(){
        Map<String,Object>requestJsonMap=new LinkedHashMap<>();
        requestJsonMap.put("name","Sena");
        requestJsonMap.put("gender","Female");
        requestJsonMap.put("phone",7897988993L);


        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage="A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Sena"));
        assertThat(response.path("data.gender"),is("Female"));

        response.prettyPrint();
    }
    @DisplayName("POST with Spartan Class")
    @Test
    public void test2(){
        Spartan spartan=new Spartan();
        spartan.setName("Sena");
        spartan.setGender("Female");
        spartan.setPhone(7897988993L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage="A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Sena"));
        assertThat(response.path("data.gender"),is("Female"));

        response.prettyPrint();
    }
    @DisplayName("POST with Spartan Class")
    @Test
    public void test3(){
        Spartan spartan=new Spartan();
        spartan.setName("Kemal");
        spartan.setGender("Male");
        spartan.setPhone(7897988993L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage="A Spartan is Born!";

       int idfromPost= given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
               .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success",is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println(idfromPost);

                 Spartan spartanPosted= given().accept(ContentType.JSON)
                                  .and().pathParam("id",idfromPost)
                                  .when().get("/api/spartans/{id}")
                                  .then().statusCode(200).log().all()
                                    .extract().as(Spartan.class);
        System.out.println(spartanPosted.getId());
        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idfromPost));
    }
}
