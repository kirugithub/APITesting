package citybiketesting;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITesting {
    @BeforeTest
    public void setUP(){
        RestAssured.baseURI = "http://api.citybik.es/v2/networks";
    }
    @Test
    public void testLocationAPI()
    {
        Response response = RestAssured.given().when().get("visa-frankfurt?fields=location").then().extract().response();
        Assert.assertEquals(200, response.statusCode(), "Status Code doesn't match");
        String responseString = response.asString();
        String city = JsonPath.parse(responseString).read("$.network.location.city");
        Assert.assertEquals("Frankfurt", city, "City doesn't match");
        String country = JsonPath.parse(responseString).read("$.network.location.country");
        Assert.assertEquals("DE", country, "Country doesn't match");
        double latitude = JsonPath.parse(responseString).read("$.network.location.latitude");
        Assert.assertEquals(50.1072, latitude, "Latitude doesn't match");
        double longitude = JsonPath.parse(responseString).read("$.network.location.longitude");
        Assert.assertEquals(8.66375, longitude, "Longitude doesn't match");

    }
}
