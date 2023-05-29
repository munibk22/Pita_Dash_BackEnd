package com.munib.dash.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

//@Configuration
//@PropertySource(".env")
//@Data
public class AppConfig {
    @Value("${DB_HOST}")
    private String dbHost;

    @Value("${DB_PORT}")
    private int dbPort;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;
//	@Value("${PUBLISH_KEY_TEST}")
//	private String publishKeyTest;

	// Getters and setters (optional)
	// ...
}
