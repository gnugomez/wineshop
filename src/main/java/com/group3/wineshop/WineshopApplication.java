package com.group3.wineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class WineshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WineshopApplication.class, args);
    }

}
