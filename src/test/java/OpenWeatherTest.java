import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class OpenWeatherTest {

    @Before
    public void setUp() throws IOException {
        baseURI = "http://samples.openweathermap.org/data/2.5";
    }

    @Test
    public void basicPingTest() {
        given().param("q", "London,uk").param("appid", "b1b15e88fa797225412429c1c50c122a1")
                .when().get("/weather")
                .then().statusCode(200);
    }

    @Test
    public void responseBodyVerificationTest() {
        given().param("q", "London,uk").param("appid", "b1b15e88fa797225412429c1c50c122a1")
                .when().get("/weather")
                .then().statusCode(200)
                .body("base", equalTo("stations"))
                .body("coord.lat", equalTo(51.51f))
                .body("weather[0].main", equalTo("Drizzle"));
    }

}


