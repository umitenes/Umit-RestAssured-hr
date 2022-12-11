package com.ichc.day4;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.baseURI;

public class CBTTrainingApiWithJsonPath {
    @BeforeAll
    public static void init(){
        baseURI="https://api.training.cydeo.com/student/";
    }

    @DisplayName("GET request individual students with JsonPath")
    @Test
    public void test() {

    }

}
