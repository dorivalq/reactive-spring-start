package com.reactive;

import com.reactive.config.GitRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest
public class MyClientTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void accessClientTest() {
        final Mono<GitRespository> mono = webClient.get().uri(uri -> uri.scheme("https").host("api.github.com")
                .pathSegment("repos", "spring-projects", "spring-boot").build())
                .retrieve().bodyToMono(GitRespository.class);
        final GitRespository gitRespository = mono.block();
        System.out.println("gitRespository = " + gitRespository);
    }
    @Test
    public void accessClientTest2() {
        final Mono<GitRespository> mono = webClient.get()

                .uri(uri -> uri.scheme("https").host("api.github.com")
                .pathSegment("repos", "spring-projects", "spring-boot").build())
                .retrieve().bodyToMono(GitRespository.class);
        final GitRespository gitRespository = mono.block();
        System.out.println("gitRespository = " + gitRespository);
    }
}
