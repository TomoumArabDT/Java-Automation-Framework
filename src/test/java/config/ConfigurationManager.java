package config;
import java.util.Properties;
import java.io.InputStream;

public class ConfigurationManager {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigurationManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load config file");
        }
    }

    public static String getBaseURI() {
        return properties.getProperty("baseURI");
    }
}
