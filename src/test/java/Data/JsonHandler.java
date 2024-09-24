package Data;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonHandler {

    private final ObjectMapper objectMapper;

    public JsonHandler() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Reads JSON data from a file and returns it as a Map.
     *
     * @param fileName the name of the JSON file to read from
     * @return a Map containing the JSON data
     * @throws IOException if an I/O error occurs
     */
    public Map<String, Object> readJsonFromFile(String fileName) throws IOException {
        return objectMapper.readValue(new File(fileName), new TypeReference<Map<String, Object>>() {});
    }

    /**
     * Writes a Map of data to a JSON file.
     *
     * @param fileName the name of the JSON file to write to
     * @param data     the Map containing the data to be written
     * @throws IOException if an I/O error occurs
     */
    public void writeJsonToFile(String fileName, Map<String, Object> data) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), data);
    }

    /**
     * Reads a specific value from a JSON file based on the provided key.
     *
     * @param fileName the name of the JSON file to read from
     * @param key      the key to look for in the JSON data
     * @return the value associated with the key
     * @throws IOException if an I/O error occurs
     */
    public Object getValueFromJson(String fileName, String key) throws IOException {
        Map<String, Object> jsonData = readJsonFromFile(fileName);
        return jsonData.get(key);
    }
}
