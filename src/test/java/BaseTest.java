import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class BaseTest {


    @Test
    public void basicPingTest() {
        given().when().get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1")
                .then().statusCode(200);
    }

}


