package com.reactive;

import com.reactive.config.GitRepository;
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
        final Mono<GitRepository> mono = webClient.get().uri(uri -> uri.scheme("https").host("api.github.com")
                .pathSegment("repos", "spring-projects", "spring-boot").build())
                .retrieve().bodyToMono(GitRepository.class);
        final GitRepository gitRespository = mono.block();
        System.out.println("gitRespository = " + gitRespository);
    }
    @Test
    public void accessClientTest2() {
        final Mono<GitRepository> mono = webClient.get()

                .uri(uri -> uri.scheme("https").host("api.github.com")
                .pathSegment("repos", "spring-projects", "spring-boot").build())
                .retrieve().bodyToMono(GitRepository.class);
        final GitRepository gitRespository = mono.block();
        System.out.println("gitRespository = " + gitRespository);
    }
}
