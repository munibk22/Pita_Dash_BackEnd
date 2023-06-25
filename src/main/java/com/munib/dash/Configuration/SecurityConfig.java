package com.munib.dash.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    protected void configure(HttpSecurity http) throws Exception {
        http.headers()
            .contentTypeOptions()
                .and()
            .frameOptions()
                .and()
            .xssProtection()
                .and()
            .cacheControl()
                .and()
            .httpStrictTransportSecurity()
                .and()
            .addHeaderWriter((request, response) -> {
                    response.setHeader("Content-Security-Policy", "default-src 'self'; ch-ua-form-factor 'tablet';");
            });
    }
}
