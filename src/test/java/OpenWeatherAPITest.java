import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


public class OpenWeatherAPITest {

    private static final String API_KEY = "b1b15e88fa797225412429c1c50c122a1";

    @Before
    public void setUp() throws IOException {
        baseURI = "http://samples.openweathermap.org/data/2.5";
    }

    @Test
    public void basicPingTest() {
        given().param("q", "London,uk").param("appid", API_KEY)
                .when().get("/weather")
                .then().statusCode(200);
    }

    @Test
    public void responseBodyVerificationTest() {
        given().param("q", "London,uk").param("appid", API_KEY)
                .when().get("/weather")
                .then().statusCode(200)
                .body("base", equalTo("stations"))
                .body("coord.lat", equalTo(51.51f))
                .body("weather[0].main", equalTo("Drizzle"));
    }

    @Test
    public void severalCityIdRequestTest(){
        List <String> cityIds = Arrays.asList("524901","703448","2643743");
        given().parameter("id", cityIds).param("units", "metric").param("appid", API_KEY)
                .when().get("/group")
                .then().statusCode(200)
                .body("cnt", equalTo(3))
                .body("list", hasSize(3));
    }

}


