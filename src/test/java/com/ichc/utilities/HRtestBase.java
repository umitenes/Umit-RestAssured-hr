package com.ichc.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRtestBase {
    @BeforeAll
    public static void init(){
        baseURI="http://44.203.73.41:1000/ords/hr";
    }

}
