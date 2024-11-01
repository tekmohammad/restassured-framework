package api.user;

import api.common.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserApi {


    public static Response getAllUsers() {
        return RestAssured
                .given()
                .get(Endpoints.GET_ALL_USERS);
    }

}
