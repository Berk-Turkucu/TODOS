package com.todomvc.utilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * reads the properties file configuration.properties
 */

public class ConfigurationReader {

    private static Properties properties;

    static {

        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param keyName which property is requested
     * @return String of the matches with keyname in the conf file
     */
    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }
}
