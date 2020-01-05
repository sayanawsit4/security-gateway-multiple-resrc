package com.scg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.jwt.Jwt;

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

/*    @GetMapping("/hello2")
    public String resource(@AuthenticationPrincipal Jwt jwt) {
        LOG.trace("***** JWT Headers: {}", jwt.getHeaders());
        LOG.trace("***** JWT Claims: {}", jwt.getClaims().toString());
        LOG.trace("***** JWT Token: {}", jwt.getTokenValue());
        return String.format("Resource accessed by: %s (with subjectId: %s)" ,
                jwt.getClaims().get("user_name"),
                jwt.getSubject());
    }*/

    @GetMapping("/calledForm")
    public Mono<ResponseEntity<String>> calledForm(@AuthenticationPrincipal Jwt jwt) {
        LOG.trace("***** JWT Claims: {}", jwt.getClaims().toString());
        LOG.trace("***** JWT Headers: {}", jwt.getHeaders());
        LOG.trace("***** JWT Claims: {}", jwt.getClaims().toString());
        LOG.trace("***** JWT Token: {}", jwt.getTokenValue());

        GreetingWebClient gwc = new GreetingWebClient(jwt.getTokenValue());

        //return  gwc.getResult().map( s ->  new ResponseEntity<>("Hello, Spring ", HttpStatus.OK));

        //return Mono.just(new ResponseEntity<>("Hello, Spring ", HttpStatus.OK));


        return gwc.getResult()
                .flatMap(s ->
                        gwc.getOtherResult().flatMap(
                                t -> Mono.just(new ResponseEntity<>(t + "&" + s, HttpStatus.OK))));

        //return gwc.getResult().flatMap( s ->  Mono.just(new ResponseEntity<>( s, HttpStatus.OK))).next();


        // .flatMap(s ->Mono.just(new ResponseEntity<>( s, HttpStatus.OK)));

        //return Mono.just(new ResponseEntity<>("Hello, Spring ", HttpStatus.OK));
    }
}

