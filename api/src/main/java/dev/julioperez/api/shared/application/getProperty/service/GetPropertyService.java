package dev.julioperez.api.shared.application.getProperty.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Service
public class GetPropertyService {

    public static String getPropertyByKey(String propertyKey){
        try(InputStream inputStream = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String property = properties.getProperty(propertyKey);
            if(Objects.isNull(property)) throw new RuntimeException(String.format("Property %s does exist",propertyKey));
            return property;
        } catch (IOException e) {
            log.error("Error to getPropertyByKey");
            throw new RuntimeException(e);
        }
    }
}
