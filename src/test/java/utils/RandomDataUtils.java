package utils;

import config.ConfigLoader;

/**
 * RandomDataUtils provides utility methods to generate random data for testing purposes.
 */
public class RandomDataUtils {

    /**
     * Generates a random email address with the current timestamp for uniqueness.
     * Uses the domain specified in the config file.
     *
     * @return A unique email string with the configured domain.
     */
    public static String generateRandomEmail() {
        String domain = ConfigLoader.getProperty("email.domain");
        return "user" + System.currentTimeMillis() + "@" + domain;
    }
}
