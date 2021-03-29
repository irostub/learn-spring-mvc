package com.irostub.servletserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ServletServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletServerApplication.class, args);
    }

}
