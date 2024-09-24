package Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties = new Properties();

    public ConfigReader() {
        String environment = System.getProperty("env", "dev");
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getEnvironmentProperty(String key) {
        String environment = System.getProperty("env", "dev");
        return properties.getProperty(environment + "." + key);
    }
}
