package tests.account;

import api.account.AccountApi;
import auth.TokenManager;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.JSONUtils;
import utils.RandomDataUtils;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateAccountTest tests the creation and deletion of a primary account.
 */
public class CreateAccountTest extends BaseTest {

    private static final String TEST_DATA_PATH = "src/test/resources/data/newAccountData.json";

    @Test
    public void testCreateAndDeleteAccount() throws IOException {
        // Load account data from JSON using JsonUtils
        System.out.println("Loading account data from JSON file...");
        Map<String, Object> accountData = JSONUtils.loadJsonAsMap(TEST_DATA_PATH);

        // Use RandomDataUtils to generate a unique email for the account
        String randomEmail = RandomDataUtils.generateRandomEmail();
        accountData.put("email", randomEmail);
        System.out.println("Using random email for account creation: " + randomEmail);

        // Step 1: Add Primary Account
        System.out.println("Sending request to add primary account...");
        Response addResponse = AccountApi.addPrimaryAccount(accountData);
        System.out.println("Response from add account: " + addResponse.asString());

        // Verify status and response body for account creation
        assertEquals(201, addResponse.statusCode(), "Expected status code 201 for account creation");
        String createdEmail = addResponse.jsonPath().getString("email");
        System.out.println("Email in response: " + createdEmail);
        assertEquals(accountData.get("email"), createdEmail, "Email in response should match request");

        String createdAccountId = addResponse.jsonPath().getString("id");
        assertNotNull(createdAccountId, "Account ID should be present in the response");
        System.out.println("Created Account ID: " + createdAccountId);

        // Step 2: Generate Supervisor Token
        System.out.println("Generating Supervisor token...");
        String validToken = "Bearer " + TokenManager.getSupervisorToken();
        System.out.println("Generated token: " + validToken);

        // Step 3: Delete Account
        System.out.println("Sending request to delete account with ID: " + createdAccountId);
        Response deleteResponse = AccountApi.deleteAccount(createdAccountId, validToken);
        System.out.println("Response from delete account: " + deleteResponse.asString());

        // Verify status code for account deletion
        assertEquals(202, deleteResponse.statusCode(), "Expected status code 202 for account deletion");
    }
}
