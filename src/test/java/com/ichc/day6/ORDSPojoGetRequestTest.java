package com.ichc.day6;

import com.ichc.pojo.Employee;
import com.ichc.pojo.Link;
import com.ichc.pojo.Region;
import com.ichc.pojo.Regions;
import com.ichc.utilities.HRtestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ORDSPojoGetRequestTest extends HRtestBase {

    @Test
    public void regionTest(){
        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();
        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }
    @DisplayName("Get employee ")
    @Test
    public void employeeGet(){
        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee1);
    }

    @DisplayName("Get request to region only fields test")
    @Test
    public void region(){
        Regions regions = get("/regions").then().statusCode(200).extract().as(Regions.class);
        assertThat(regions.getCount(),is(4));

        List<String>regionNames=new ArrayList<>();
        List<Integer>regionIds=new ArrayList<>();

        List<Region>items=regions.getItems();

        for (Region region : items) {
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegion_name());
        }
        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        List<Integer>expectedRegionIds= Arrays.asList(1,2,3,4);
        List<String>expecedRegionNames=Arrays.asList("Europe","Americas","Asia","Middle East and Africa");

        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expecedRegionNames)));
    }
}
