package api.account;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.common.Endpoints;

import java.util.Map;

/**
 * AccountApi contains methods to interact with the account-related endpoints.
 */
public class AccountApi {

    /**
     * Sends a request to get all accounts.
     *
     * @return Response object containing the API response
     */
    public static Response getAllAccounts() {
        return RestAssured
                .given()
                .get(Endpoints.GET_ALL_ACCOUNTS);
    }

    /**
     * Sends a request to get all accounts with authorization.
     *
     * @param token Authorization token to include in the request
     * @return Response object containing the API response
     */
    public static Response getAllAccounts(String token) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .get(Endpoints.GET_ALL_ACCOUNTS);
    }

    /**
     * Adds a primary account using the provided account data.
     *
     * @param accountData A map containing account data for the request body.
     * @return Response of the add account API call.
     */
    public static Response addPrimaryAccount(Map<String, Object> accountData) {
        return RestAssured
                .given()
                .contentType("application/json")
                .body(accountData)
                .post(Endpoints.ADD_PRIMARY_ACCOUNT);
    }

    /**
     * Deletes an account with the specified primaryPersonId.
     *
     * @param accountId The ID of the primary person account to delete.
     * @param token     Authorization token for the request.
     * @return Response of the delete account API call.
     */
    public static Response deleteAccount(String accountId, String token) {
        return RestAssured
                .given()
                .header("Authorization", token)
                .param("primaryPersonId", accountId)
                .delete(Endpoints.DELETE_ACCOUNT);
    }
}
