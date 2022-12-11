package com.ichc.day5;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.ichc.utilities.DButils;
import com.ichc.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class SpartanApiVsDB extends SpartanTestBase {
    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1(){
        String query="select spartan_id,name,gender,phone from spartans\n" +
                "where spartan_id=15";
        Map<String,Object>dbMap=DButils.getRowMap(query);
        System.out.println(dbMap);


        Map<String,Object>apiMap=given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response().as(Map.class);

        System.out.println(apiMap);

        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));


    }
}
