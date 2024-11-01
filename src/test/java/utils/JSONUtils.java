package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSONUtils {

    /**
     * Loads JSON test data from a specified file path and converts it to a Map.
     *
     * @param filePath Path to the JSON file containing test data.
     * @return A Map representation of the JSON data.
     * @throws IOException If an error occurs while reading the file.
     */
    public static Map<String, Object> loadJsonAsMap(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), Map.class);
    }

}
