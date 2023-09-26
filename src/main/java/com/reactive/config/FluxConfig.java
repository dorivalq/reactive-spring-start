package com.reactive.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class FluxConfig {

    @PostConstruct
    private void addHooks() {
        Hooks.onEachOperator(FluxConfig.class.getName(),
                Operators.lift((scannable, coreSubscriber) -> new MdcContextPopulator<>(coreSubscriber))
        );
        Hooks.onErrorDropped(throwable -> {
            if (throwable instanceof UnsupportedOperationException &&
                    "org.springframework.http.ReadOnlyHttpHeaders".equals(throwable.getStackTrace()[0].getClassName())) {
                log.info("Unsupported operation on read-only headers.");
            } else {
                log.error("onErrorDropped", throwable);
            }
        });
    }

    @Bean
    WebClient client() {
        return WebClient.create("https://www.google.com.br");
    }

}
