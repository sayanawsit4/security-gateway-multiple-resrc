package com.scg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecuredServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuredServiceApplication.class, args);
    }

    private static final Logger LOG = LoggerFactory.getLogger(SecuredServiceApplication.class);


}
