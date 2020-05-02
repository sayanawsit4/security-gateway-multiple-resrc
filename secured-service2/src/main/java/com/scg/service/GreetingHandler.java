package com.scg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GreetingHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingHandler.class);

    @GetMapping("/hello")
    public Mono<ResponseEntity<String>> hello() {
        return Mono.just(new ResponseEntity<>("Hello, Spring ", HttpStatus.OK));
    }

    @GetMapping("/hello2")
    public Mono<ResponseEntity<String>> hello2() {
        return Mono.just(new ResponseEntity<>("Hello, Spring 2", HttpStatus.OK));
    }

}