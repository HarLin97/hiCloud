package com.fuchangling.auth.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author harlin
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class);
    }
}
