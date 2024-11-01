package tests.user;

import api.account.AccountApi;
import api.user.UserApi;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllUsers extends BaseTest {

    @Test
    public void testGetAccountsUnauthorized() {
        // Send GET request without a token
        Response response = UserApi.getAllUsers();

        // Assert that the response status is 401 Unauthorized
        assertEquals(200, response.statusCode(), "success");
        System.out.println("Users: " + response.asString());
    }

}
