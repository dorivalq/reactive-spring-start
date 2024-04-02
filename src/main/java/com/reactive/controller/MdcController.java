package com.reactive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/mdc")
public class MdcController {

    Logger log = LoggerFactory.getLogger(MdcController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String getPrice(){
        final HttpEntity<?> httpEntity = getHttpEntity();
//        final HttpEntity<?> httpEntity = getHttpEntity2();
//        final ResponseEntity<String> exchange = restTemplate.exchange("http://mockbin.com/request?foo=bar&foo=baz", HttpMethod.POST, httpEntity, String.class);
        final ResponseEntity<String> exchange = restTemplate.exchange("http://mockbin.org/bin/c1a56751-1099-48fa-a5e1-631e802c4cd0?foo=bar&foo=baz", HttpMethod.POST, httpEntity, String.class);
        log.info("exchange = " + exchange.getStatusCode());
        log.info("exchange = " + exchange.getBody());
        return exchange.getBody()+" \n CODE: "+exchange.getStatusCode()+" \n HEADERS; "+exchange.getHeaders();
    }

    @Autowired
    private WebClient webClient;

    @GetMapping("/1")
    public String getPrice1(){
         webClient.post().uri("http://mockbin.org/bin/c1a56751-1099-48fa-a5e1-631e802c4cd0?foo=bar&foo=baz")
                .header("x-key", "adpadpadp")
//                .exchangeToMono(mono -> mono.bodyToMono(String.class))
//                .map(response -> response)
                //.share().block();
//                .subscribe();
        .retrieve()
                .bodyToMono(String.class).subscribe( ev ->  log.info("result = " + ev ));
        return "OK";
    }

    private HttpEntity<?> getHttpEntity() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("cookie", "foo=bar; bar=baz");
        httpHeaders.add("x-pretty-print", "2");
        final HttpEntity<?> httpEntity = new HttpEntity<>("{\"foo\": \"bar\"}", httpHeaders);
        return httpEntity;
    }
    private HttpEntity<?> getHttpEntity2() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("accept", "application/json");
        httpHeaders.add("cookie", "foo=bar; bar=baz");
        httpHeaders.add("x-pretty-print", "2");
        final HttpEntity<?> httpEntity = new HttpEntity<>("{\"foo\": \"bar\"}", httpHeaders);
        return httpEntity;
    }
}
