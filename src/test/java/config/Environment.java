package config;

/**
 * Environment enum represents different environments in which tests can run.
 * It provides specific base URLs and other environment-specific configurations.
 */
public enum Environment {
    DEV("https://dev.insurance-api.tekschool-students.com"),
    QA("https://qa.insurance-api.tekschool-students.com"),
    USERS("https://jsonplaceholder.typicode.com"),
    PROD("https://api.example.com");

    private final String baseURL;

    Environment(String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * Retrieves the base URL for the environment.
     *
     * @return The base URL as a String
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     * Loads the current environment based on configuration settings.
     *
     * @return The Environment enum representing the current environment
     */
    public static Environment getCurrentEnvironment() {
        String env = ConfigLoader.getProperty("environment").toUpperCase();
        return Environment.valueOf(env);
    }
}
