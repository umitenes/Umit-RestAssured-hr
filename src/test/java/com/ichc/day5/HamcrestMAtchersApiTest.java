package com.ichc.day5;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class HamcrestMAtchersApiTest {
    @DisplayName("Onespartan with Hamcrest and chaining")
    @Test
    public void test(){
        given().log().all().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when()
                .get("http://54.236.150.168:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15),
                        "name",is("PutRequest"),
                                    "gender",is("Male"),
                                    "phone",is(44444444444L))
                .log().all();
    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void test1(){
          given().accept(ContentType.JSON)
                .and()
                .pathParam("id","5")
                .when()
                .get("https://api.training.cydeo.com/teachers/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Date",notNullValue())
                  .and().assertThat()
                .body("firstName",is("Mario"))
                .body("lastName",is("Luigi"))
                  .body("gender",is("Male"));

    }
    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .when()
                .get("https://api.training.cydeo.com/teachers/")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Valter","Mario","Tory"));

    }


}
