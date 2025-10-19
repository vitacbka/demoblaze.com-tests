package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = TestConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не удалось найти config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении config.properties", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getValidUsername() {
        return properties.getProperty("valid.username");
    }

    public static String getValidPassword() {
        return properties.getProperty("valid.password");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalid.password");
    }

    public static String getInvalidUsername() {
        return properties.getProperty("invalid.username");
    }
}