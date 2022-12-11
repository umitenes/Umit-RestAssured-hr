package com.ichc.day4;
import com.ichc.utilities.HRtestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiWithJsonPath extends HRtestBase {
    @DisplayName("GET request to countries")
    @Test
    public void test() {
        Response response = get("/countries");

        JsonPath jsonPath=response.jsonPath();

        String countryName=jsonPath.getString("items[1].country_name");
        System.out.println(countryName);

        List<String> allCountries=jsonPath.getList("items.country_id");
        System.out.println(allCountries);

       List<String>CountryNamewithRegion=jsonPath.getList("items.findAll{it.region_id==3}.country_name");
        System.out.println(CountryNamewithRegion);
    }
    @DisplayName("GET request /employees with query param")
    @Test
    public void test1(){
        Response response=given().queryParam("limit",107)
                .when().get("/employees");
        JsonPath jsonPath=response.jsonPath();
        List<String>employeeItProgram=jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeItProgram);

        List<String>employeesSalary=jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println(employeesSalary);

        String kingFirstName=jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(kingFirstName);
        String kingreposnFirsname=response.path("items.max{it.salary}.first_name");
        System.out.println(kingreposnFirsname);
    }


}
