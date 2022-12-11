package com.ichc.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {
    @BeforeAll
    public static void init(){
        baseURI="http://44.203.73.41:8000";
        String dbUrl="jdbc:oracle:thin:@44.203.73.41:1521:XE";
        String dbUsername="SP";
        String dbPassword="SP";
        //DButils.createConnection(dbUrl,dbUsername,dbPassword);
    }
    @AfterAll
    public static void teardown(){
       // DButils.destroy();
    }

}
