package com.ichc.day4;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.ichc.utilities.HRtestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ORDSApiTestwithPath extends HRtestBase {
    @DisplayName("GET request to countries with Path method")
    @Test
    public void test(){
        Response response=given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .when()
                .get("/countries");


        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

      String firstCountryId=response.path("items[0].country_id");
        System.out.println(firstCountryId);
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        System.out.println("response.path(\"items[3].links[0].href\") = " + response.path("items[3].links[0].href"));

        List<String> countryNames=response.path("items.country_name");
        System.out.println(countryNames);

        List<Integer> allRegions=response.path("items.region_id");
        for (Integer allRegion : allRegions) {
            System.out.println(allRegion);
            assertEquals(2,allRegion);
        }
    }

}
