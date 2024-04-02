package com.reactive.config;

import io.jaegertracing.SpanContext;
import io.opentracing.Span;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import reactor.core.CoreSubscriber;
import reactor.util.context.Context;

@Slf4j
public class MdcContextPopulator<T> implements CoreSubscriber<T> {

    private static final Logger LOG = LoggerFactory.getLogger(MdcContextPopulator.class);

    public static final String SPAN_KEY = Span.class.getName();

    private CoreSubscriber<T> coreSubscriber;

    public MdcContextPopulator(CoreSubscriber<T> coreSubscriber) {
        this.coreSubscriber = coreSubscriber;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        coreSubscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T obj) {

        Context context = currentContext();
        log.info(context + " ##### Obj: " + obj);
        clear();

        if (context.hasKey(SPAN_KEY)) {
            Span span = context.get(SPAN_KEY);
            populate(span);
        }

        coreSubscriber.onNext(obj);
    }

    @Override
    public void onError(Throwable t) {
        coreSubscriber.onError(t);
    }

    @Override
    public void onComplete() {
        coreSubscriber.onComplete();
    }

    @Override
    public Context currentContext() {
        return coreSubscriber.currentContext();
    }

    public static void clear() {
        MDC.clear();
    }

    public static void populate(Span span) {
        io.opentracing.SpanContext spanContext = null;// span.context();

        try {
            spanContext = (SpanContext) FieldUtils.readField(span, "context", true);
            final io.jaegertracing.Span span1 = (io.jaegertracing.Span) span;
            final SpanContext context = span1.context();
            MDC.put("traceid", String.valueOf(context.getTraceId()));
            MDC.put("hextraceid", Long.toHexString(context.getTraceId()));
            MDC.put("spanid", String.valueOf(context.getSpanId()));

            spanContext.baggageItems().forEach(e -> MDC.put(e.getKey(), e.getValue()));

            if (LOG.isDebugEnabled()) {
                LOG.debug("Populated the MDC Context. Current context {}", MDC.getCopyOfContextMap());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
