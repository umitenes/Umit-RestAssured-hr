package com.ichc.day5;
import com.ichc.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SpartanHamcrest extends SpartanTestBase {
    @DisplayName("GEt spartan / search and chaining together")
    @Test
    public void test(){

        List<String> names=given().accept(ContentType.JSON)
                .and().queryParams("nameContains","j","gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement",greaterThanOrEqualTo(3))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(names);
    }
    @DisplayName("GEt spartan / search and chaining together")
    @Test
    public void test1(){

        int statusCode=given().accept(ContentType.JSON)
                .and().queryParams("nameContains","j",
                        "gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement",greaterThanOrEqualTo(3))
                .extract().response().statusCode();
        System.out.println(statusCode);

    }

}
