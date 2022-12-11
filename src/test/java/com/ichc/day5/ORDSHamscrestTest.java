package com.ichc.day5;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.ichc.utilities.HRtestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamscrestTest extends HRtestBase {
    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void test(){
        List<String> names= Arrays.asList("Alexander","Bruce","David","Valli","Diana");
        given().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees/")
                .then()
                .statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .body("items.first_name",containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"))
                .body("items.first_name",equalToObject(names));

    }
    @DisplayName("get response and chain")
    @Test
    public void test1(){
       JsonPath jsonPath =given().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees/")
                .then()
                .statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();
       assertThat(jsonPath.getList("items.first_name"),hasSize(5));
       assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));


    }
}
