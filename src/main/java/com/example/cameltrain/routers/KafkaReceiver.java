package com.example.cameltrain.routers;

import com.example.cameltrain.service.SumService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaReceiver extends RouteBuilder {
    private final SumService sumService;

    @Override
    public void configure() {
        from("timer:test?period=6000&delay=6000")
                .transform().method("sumService", "getSum")
                .log("sum of values in last 60 seconds : " + "${body}");

        from("kafka:topic1")
                .process(sumService);
    }
}
