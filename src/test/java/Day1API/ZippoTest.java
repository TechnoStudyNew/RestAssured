package Day1API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    @BeforeClass
    public void setUp(){
        baseURI = "https://api.zippopotam.us";
    }

    @Test
    public void test(){

        given()
                .when()
                .then();

    }

    @Test
    public void checkingStatusCode(){

        given()
                //.when().get("https://api.zippopotam.us/us/08536")
                .when().get("/us/08536")
                .then().statusCode(200);
    }

    @Test
    public void loggingRequestDetails(){
        given().log().all()
                //.when().get("https://api.zippopotam.us/us/08536")
                .when().get("/us/08536")
                .then();
    }

    @Test
    public void loggingResponseDetails(){
        given()
                //.when().get("https://api.zippopotam.us/us/08536")
                .when().get("/us/08536")
                //.then().log().all();
                .then().log().body()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    /*
    {
    "post code": "08536",
    "country": "United States",
    "country abbreviation": "US",
    "places": [
        {
            "place name": "Plainsboro",
            "longitude": "-74.5688",
            "state": "New Jersey",
            "state abbreviation": "NJ",
            "latitude": "40.3324"
        }
    ]
}
     */
    @Test
    public void checkContentType(){
        given()
                //.when().get("https://api.zippopotam.us/us/08536")
                .when().get("/us/08536")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void checkCountryTest(){
        given()
                //.when().get("https://api.zippopotam.us/us/08536")
                .when().get("/us/08536")
                .then()
                .body("country",equalTo("United States"))
                .statusCode(200);
    }

    @Test
    public void validateCountryAbv(){
        given()
                //.when().get("https://api.zippopotam.us/us/08536")
                .when().get("/us/08536")
                .then()
                .body("'country abbreviation'",equalTo("US"))   //Assertion
                .body("places[0].state",equalTo("New Jersey"))  //Assertion
                .statusCode(200);
    }

    @Test
    public void pathParameterTest(){
        String country = "us";
        String zipCode = "08536";

        given()
                .pathParams("country",country)
                .pathParams("zipCode",zipCode)
                .when().get("/{country}/{zipCode}")
                .then()
                .statusCode(200);
    }

    @Test
    public void queryParameter(){
        String gender = "female";
        String status = "active";

        given()
                .queryParam("gender",gender)
                .queryParam("status",status)
                .when().get("https://gorest.co.in/public/v2/users")
                .then()
                .statusCode(200)
                .log().body();
    }


        @Test
        public void extractValueTest(){
            Object countryInfo=given()
                    .when().get("/us/08536")
                    .then()
                    .extract().path("country");
            System.out.println(countryInfo);
    }



}
