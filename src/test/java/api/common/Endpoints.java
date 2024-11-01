package api.common;

/**
 * Endpoints contains all API paths used throughout the framework,
 * centralizing endpoint management for maintainability.
 */
public class Endpoints {
    public static final String AUTH_ENDPOINT = "/api/token";
    public static final String GET_ALL_ACCOUNTS = "/api/accounts/get-all-accounts";
    public static final String ADD_PRIMARY_ACCOUNT = "/api/accounts/add-primary-account";
    public static final String DELETE_ACCOUNT = "/api/accounts/delete-account";
    public static final String GET_ALL_USERS = "/users";
}
