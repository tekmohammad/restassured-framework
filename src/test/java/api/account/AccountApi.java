package api.account;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.common.Endpoints;

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
}
