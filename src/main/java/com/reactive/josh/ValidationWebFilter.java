//package com.reactive.josh;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Component
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
//public class ValidationWebFilter implements WebFilter {
//
//    private static final String ERR_MSG = "An error has occurred ###";
//    @Autowired
//    private RequestMappingHandlerMapping handlerMapping;
//
//    @NonNull
//    @Override
//    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
//        log.info("%%% Verifying the Headers %%% " );
//        exchange.getRequest().getHeaders().forEach((k,v) -> log.info(k+" ###  "+v ));
//
//        return handlerMapping.getHandler(exchange)
//            .doOnNext(handler -> validateParameters(handler, exchange))
//            .then(chain.filter(exchange));
//    }
//
//    private void validateParameters(Object handler, ServerWebExchange exchange) {
//        if (handler instanceof HandlerMethod && 1==2) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Set<String> expectedQueryParams = Arrays.stream(handlerMethod.getMethodParameters())
//                .map(param -> param.getParameterAnnotation(RequestParam.class))
//                .filter(Objects::nonNull)
//                .map(RequestParam::name)
//                .collect(Collectors.toSet());
//            Set<String> actualQueryParams = exchange.getRequest().getQueryParams().keySet();
//            actualQueryParams.forEach(actual -> {
//                if (!expectedQueryParams.contains(actual)) {
//                    throw new IllegalArgumentException(ERR_MSG);
//                }
//            });
//        }
//    }
//}