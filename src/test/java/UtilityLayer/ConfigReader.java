package UtilityLayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

	private static final Logger logger = LogManager.getLogger(ConfigReader.class);
	private static Properties properties = new Properties();

	static {
		try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
			properties.load(fis);
			logger.info("Configuration properties loaded successfully.");
		} catch (IOException e) {
			logger.error("Failed to load configuration properties.", e);
		}
	}

	public static String getProperty(String key, String defaultValue) {
		String value = properties.getProperty(key, defaultValue);
		if (value == null) {
			logger.warn("Key '{}' not found. Returning default value '{}'.", key, defaultValue);
		}
		return value;
	}

	public static String getProperty(String key) {
		return getProperty(key, null);
	}
}
