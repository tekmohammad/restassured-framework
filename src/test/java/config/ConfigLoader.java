package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigLoader is responsible for loading configurations from the properties file
 * and providing easy access throughout the framework.
 */
public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (input == null) {
                throw new RuntimeException("Could not find config.properties file in resources/config/");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration properties file: config/config.properties", e);
        }
    }

    /**
     * Retrieves the property value based on the given key.
     *
     * @param key The property key to fetch the value for
     * @return The value associated with the provided key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Retrieves an integer property value based on the given key.
     *
     * @param key The property key to fetch the integer value for
     * @return The integer value associated with the provided key
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
