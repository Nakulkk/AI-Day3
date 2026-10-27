package payments;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.config.HeaderConfig.headerConfig;

public class BaseTest {

    @BeforeEach
    public void setup() {
        String apiKey = System.getenv("API_KEY.bat");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("API_KEY.bat environment variable is not set.");
        }
        RestAssured.baseURI = "https://api.restcountries.com";

        RestAssured.config = RestAssured.config()
                .headerConfig(headerConfig().overwriteHeadersWithName("Authorization"));

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + apiKey.trim())
                .build();
    }
}