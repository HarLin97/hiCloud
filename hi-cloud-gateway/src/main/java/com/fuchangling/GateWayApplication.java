package com.fuchangling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关
 *
 * @author harlin
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class);
    }
}
