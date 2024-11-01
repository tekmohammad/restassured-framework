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

/**
 * DeleteAccountTest verifies the deletion of an account with different user roles.
 */
public class DeleteAccountTest extends BaseTest {

    private static final String TEST_DATA_PATH = "src/test/resources/data/newAccountData.json";

    @Test
    public void testDeleteAccountWithDifferentUsers() throws IOException {
        // Step 1: Create Account with Random Email
        Map<String, Object> accountData = JSONUtils.loadJsonAsMap(TEST_DATA_PATH);
        accountData.put("email", RandomDataUtils.generateRandomEmail());

        System.out.println("Creating account with random email: " + accountData.get("email"));
        Response createAccountResponse = AccountApi.addPrimaryAccount(accountData);
        assertEquals(201, createAccountResponse.statusCode(), "Expected status code 201 for account creation");

        String newAccountId = createAccountResponse.jsonPath().getString("id");
        System.out.println("Created Account ID: " + newAccountId);

        // Step 2: Attempt Delete with Operator Token (Expect 403)
        String operatorToken = "Bearer " + TokenManager.getOperatorToken();
        System.out.println("Attempting to delete account with Operator token...");
        Response deleteWithOperatorResponse = AccountApi.deleteAccount(newAccountId, operatorToken);
        assertEquals(403, deleteWithOperatorResponse.statusCode(), "Expected status code 403 for operator deletion attempt");

        // Step 3: Attempt Delete with Supervisor Token (Expect 202)
        String supervisorToken = "Bearer " + TokenManager.getSupervisorToken();
        System.out.println("Attempting to delete account with Supervisor token...");
        Response deleteWithSupervisorResponse = AccountApi.deleteAccount(newAccountId, supervisorToken);
        assertEquals(202, deleteWithSupervisorResponse.statusCode(), "Expected status code 202 for supervisor deletion");

        System.out.println("Account deleted successfully with Supervisor token.");
    }
}
