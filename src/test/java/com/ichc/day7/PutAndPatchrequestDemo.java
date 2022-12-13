package com.ichc.day7;
import com.ichc.pojo.Spartan;
import com.ichc.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class PutAndPatchrequestDemo extends SpartanTestBase{
    @DisplayName("Put a request")
    @Test
    public void putRequest(){
        Map<String,Object> putRequestMap=new LinkedHashMap<>();
        putRequestMap.put("name","BruceWills");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone","1234567890");

        given().contentType(ContentType.JSON)
                .body(putRequestMap)
                .when().put("/api/spartans/{id}", 116)
                .then()
                .statusCode(204);


    }
    @DisplayName("Patch a request")
    @Test
    public void patchRequest(){
        Map<String,Object> putRequestMap=new LinkedHashMap<>();
        putRequestMap.put("phone",5009876432L);
        putRequestMap.put("name","Selami");

        given().contentType(ContentType.JSON)
                .body(putRequestMap)
                .and().pathParam("id", 116)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @DisplayName("Deelte a request")
    @Test
    public void deleteRequest(){
        int idToDeelte=115;
        given().pathParam("id",idToDeelte)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);


        Spartan spartanPosted= given().accept(ContentType.JSON)
                .and().pathParam("id",idToDeelte)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404).log().all()
                .extract().as(Spartan.class);

       //assertThat(idToDeelte,is(spartanPosted));
    }
}
