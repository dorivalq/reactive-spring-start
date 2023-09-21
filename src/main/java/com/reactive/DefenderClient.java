package com.reactive;

import com.reactive.domain.LoginRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.util.concurrent.ExecutionException;

public class DefenderClient {


    public ResponseEntity<String> returnClient() {
        boolean isEnabled = false;

        if (isEnabled) {
            calledMonoError();
            return null;
        }

        if (isEnabled) {
            LoginRequest login = new LoginRequest();
            login.setUser("pi_appuser");
            login.setPassword("ADPadp11");
            postMdcProxy(login);
        }

        blockedMonoReturn();

        return null;
    }

    private static void calledMonoError() {
        WebClient webClient = WebClient.builder()
//                   .baseUrl("http://mdcore-opswat-ditfit.es.oneadp.com:8008/login")
                .baseUrl("https://google.com.br")
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<ResponseEntity<String>> mono = webClient.post().retrieve().toEntity(String.class);
        mono.block();
    }

    private static void blockedMonoReturn() {
        Mono<Object> objectMono = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()))
                .baseUrl("https://google.com.br")
                .build()
                .get()
                //.retrieve().toEntity(String.class).block();
                .exchange()
                .flatMap(response -> {
                    if (MediaType.APPLICATION_JSON_UTF8.equals(response.headers().contentType().get())) {
                        System.out.println("response = " + response);
                        return null;
                    } else {
                        System.out.println("response = " + response);
                        return null;
                    }
                });
        try {
            objectMono.toFuture().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void postMdcProxy(LoginRequest login) {
        HttpClient httpClient = HttpClient.create().proxy(pr1 -> pr1.type(ProxyProvider.Proxy.HTTP)
                .host("http://tsrcopralphis:totalsource@dc01usproxy.es.oneadp.com").port(8080)
                .nonProxyHosts("localhost,127.0.0.1,.adp.com,.es.ad.adp.com,.aws.adp.com,.aws.piadp.com"));
        final Mono<Object> objectMono = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .baseUrl("http://mdcore-opswat-ditfit.es.oneadp.com:8008")
                .baseUrl("https://google.com.br")
                .build().post()
                .body(Mono.just(login), LoginRequest.class)
                //.retrieve().toEntity(String.class).block();
                .exchange()
                .flatMap(response -> {
                    if (MediaType.APPLICATION_JSON_UTF8.equals(response.headers().contentType().get())) {
                        System.out.println("response = " + response);
                        return null;
                    } else {
                        System.out.println("response = " + response);
                        return null;
                    }
                });
        try {
            objectMono.toFuture().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private HttpClient httpClient() {
        final HttpClient httpClient = HttpClient.create();
        httpClient.proxy(p ->
                p.type(ProxyProvider.Proxy.HTTP)
                        .host("http://tsrcopralphis:totalsource@dc01usproxy.es.oneadp.com")
                        .port(8080)
                        .nonProxyHosts("localhost,127.0.0.1,.adp.com,.es.ad.adp.com,.aws.adp.com,.aws.piadp.com"));

        return httpClient;

    }

}
