package auth;

import config.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import config.ConfigLoader;
import api.common.Endpoints;

import static io.restassured.RestAssured.given;

public class TokenGenerator {

    /**
     * Generates a token for a given username and password.
     *
     * @param username The username for the login request
     * @param password The password for the login request
     * @return The generated token as a String
     */
    private static String generateToken(String username, String password) {
        Environment currentEnv = Environment.getCurrentEnvironment();
        RestAssured.baseURI = currentEnv.getBaseURL();

        Response response = given()
                .contentType("application/json")
                .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .post(Endpoints.AUTH_ENDPOINT);

        if (response.statusCode() == 200) {
            return response.jsonPath().getString("token");
        } else {
            throw new RuntimeException("Failed to generate token for user: " + username +
                    ". Status code: " + response.statusCode());
        }
    }

    /**
     * Retrieves and generates a valid token for the Supervisor role by
     * fetching the username and password from the configuration file.
     *
     * @return The generated token for the Supervisor role as a String
     */
    public static String getSupervisorToken() {
        String username = ConfigLoader.getProperty("supervisor.username");
        String password = ConfigLoader.getProperty("supervisor.password");
        return generateToken(username, password);
    }

    /**
     * Retrieves and generates a valid token for the Operator role by
     * fetching the username and password from the configuration file.
     *
     * @return The generated token for the Operator role as a String
     */
    public static String getOperatorToken() {
        String username = ConfigLoader.getProperty("operator.username");
        String password = ConfigLoader.getProperty("operator.password");
        return generateToken(username, password);
    }
}
