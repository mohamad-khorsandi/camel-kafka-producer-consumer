package ir.sobhan.camelKafkaProducerConsumer.routers;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSender extends RouteBuilder {
    @Override
    public void configure() {
        from("timer:test?period=1000")
                .transform().method("genRandom")
                .log("produced : ${body}")
                .to("kafka:topic1");
    }
}