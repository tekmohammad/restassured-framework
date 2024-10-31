package auth;

import java.time.Instant;
import config.ConfigLoader;

/**
 * TokenManager handles token retrieval and management for various roles.
 * This class ensures that each test has the necessary tokens available
 * for authorization, with caching and expiration checks to avoid redundant
 * API calls and refresh tokens when they expire.
 */
public class TokenManager {

    // Cached tokens for Supervisor and Operator roles
    private static String supervisorToken;
    private static String operatorToken;

    // Timestamps to track when tokens were generated
    private static Instant supervisorTokenGeneratedAt;
    private static Instant operatorTokenGeneratedAt;

    // Load token expiration time from config
    private static final long TOKEN_EXPIRATION_MINUTES = ConfigLoader.getIntProperty("token.expiration.minutes");

    /**
     * Retrieves a valid token for the Supervisor role. If a cached token
     * is present and valid, it returns the cached token; otherwise, it
     * generates a new token.
     *
     * @return A valid Supervisor token as a String
     */
    public static String getSupervisorToken() {
        if (supervisorToken == null || isTokenExpired(supervisorTokenGeneratedAt)) {
            supervisorToken = TokenGenerator.getSupervisorToken();
            supervisorTokenGeneratedAt = Instant.now();
        }
        return supervisorToken;
    }

    /**
     * Retrieves a valid token for the Operator role. If a cached token
     * is present and valid, it returns the cached token; otherwise, it
     * generates a new token.
     *
     * @return A valid Operator token as a String
     */
    public static String getOperatorToken() {
        if (operatorToken == null || isTokenExpired(operatorTokenGeneratedAt)) {
            operatorToken = TokenGenerator.getOperatorToken();
            operatorTokenGeneratedAt = Instant.now();
        }
        return operatorToken;
    }

    /**
     * Checks if the token has expired based on the time it was generated.
     *
     * @param generatedAt The timestamp when the token was generated
     * @return true if the token has expired; otherwise, false
     */
    private static boolean isTokenExpired(Instant generatedAt) {
        if (generatedAt == null) return true;
        Instant expirationTime = generatedAt.plusSeconds(TOKEN_EXPIRATION_MINUTES * 60);
        return Instant.now().isAfter(expirationTime);
    }

    /**
     * Clears all cached tokens to ensure they are removed after the test completes.
     * This should be called in the tear down process of each test to maintain security.
     */
    public static void clearTokens() {
        supervisorToken = null;
        operatorToken = null;
        supervisorTokenGeneratedAt = null;
        operatorTokenGeneratedAt = null;
    }
}
