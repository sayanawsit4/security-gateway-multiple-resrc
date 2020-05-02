package com.scg.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GreetingWebClient {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingWebClient.class);

    private String jwt;

    GreetingWebClient(String _jwt) {
        this.jwt = "Bearer " + _jwt;
    }

    private WebClient client = WebClient.create("http://resource2:9001");


    public Mono<String> getResult() {

        Mono<ClientResponse> result1 = client.get()
                .uri("/hello")
                //.header("Authorization", this.jwt)
                .headers(headers ->
                        headers.add("Authorization", this.jwt)
                ).accept(MediaType.TEXT_PLAIN)
                .exchange();

        return result1.flatMap(res -> res.bodyToMono(String.class));
    }

    public Mono<String> getOtherResult() {

        Mono<ClientResponse> result2 = client.get()
                .uri("/hello2")
                .headers(headers ->
                        headers.add("Authorization", this.jwt)
                ).accept(MediaType.TEXT_PLAIN)
                .exchange();

        return result2.flatMap(res -> res.bodyToMono(String.class));
    }
}