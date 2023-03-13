package com.codecool.shop.dao.database;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationProperties {
    private final Properties properties;


    public ApplicationProperties() {
        this.properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("connection.properties"));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.ALL, "Exception occurred while loading properties.");
        }

    }

    public String getProperty(String keyName) {
        return properties.getProperty(keyName, "There is no key in the property file.");
    }
}
