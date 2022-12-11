package com.ichc.day5;
import com.ichc.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class JsonToJavaTest extends SpartanTestBase {
    @DisplayName("GET one Spartan and deserilize to Map")
    @Test
    public void test(){
        Response response=given().pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();
        Map<String,Object> jsonMap=response.as(Map.class);
        System.out.println(jsonMap);
        String actualNme=(String) jsonMap.get("name");
       assertThat(actualNme,is("Meta"));
    }
    @DisplayName("GET all spartans to JAVA")
    @Test
    public void test2(){
        Response response=get("/api/spartans").then().statusCode(200).extract().response();

        List<Map<String,Object>>jsonList=response.as(List.class);
        System.out.println("jsonList.get(0) = " + jsonList.get(1).get("name"));

        Map<String,Object>spartan3=jsonList.get(2);
        System.out.println(spartan3);
    }
}
