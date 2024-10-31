package base;

import config.ConfigLoader;
import config.Environment;
import auth.TokenManager;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

/**
 * BaseTest sets up the common configurations and settings for API tests,
 * ensuring proper initialization of base URL and timeout, and handling
 * token cleanup after each test for security.
 */
public class BaseTest {

    @BeforeEach
    public void setUp() {
        // Set the base URL dynamically based on the current environment
        Environment currentEnv = Environment.getCurrentEnvironment();
        RestAssured.baseURI = currentEnv.getBaseURL();

        // Set the timeout from config
        int timeout = ConfigLoader.getIntProperty("timeout");
        RestAssured.config = RestAssured.config().httpClient(
                RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout", timeout)
        );

        System.out.println("Environment: " + currentEnv);
        System.out.println("Base URL set to: " + RestAssured.baseURI);
        System.out.println("Timeout set to: " + timeout + "ms");
    }

    @AfterEach
    public void tearDown() {
        // Clear cached tokens after each test for security
        TokenManager.clearTokens();
        System.out.println("Tokens cleared after test completion.");
    }
}
