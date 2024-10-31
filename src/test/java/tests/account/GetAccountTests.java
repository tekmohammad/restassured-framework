package tests.account;

import api.account.AccountApi;
import auth.TokenManager;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * GetAccountTests contains test cases to validate the behavior of the
 * get-all-accounts endpoint. These tests cover scenarios for both
 * unauthorized and authorized access.
 */
public class GetAccountTests extends BaseTest {

    /**
     * Test case to validate unauthorized access to the get-all-accounts endpoint.
     * This test sends a request without an authorization token and expects a 401 Unauthorized status.
     */
    @Test
    public void testGetAccountsUnauthorized() {
        // Send GET request without a token
        Response response = AccountApi.getAllAccounts();

        // Assert that the response status is 401 Unauthorized
        assertEquals(401, response.statusCode(), "Expected 401 status when no token is provided");
        System.out.println("Unauthorized access to /get-all-accounts endpoint returned status: " + response.statusCode());
    }

    /**
     * Test case to validate authorized access to the get-all-accounts endpoint.
     * This test uses a valid Supervisor token and expects a 200 OK status.
     */
    @Test
    public void testGetAccountsWithAuthorization() {
        // Generate a valid Supervisor token
        String token = TokenManager.getSupervisorToken();

        // Send GET request with the token in the Authorization header
        Response response = AccountApi.getAllAccounts(token);

        // Assert that the response status is 200 OK
        assertEquals(200, response.statusCode(), "Expected 200 status with valid Supervisor token");
        System.out.println("Authorized access to /get-all-accounts endpoint returned status: " + response.statusCode());
    }
}
