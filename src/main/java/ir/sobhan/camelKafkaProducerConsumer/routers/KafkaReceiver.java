package ir.sobhan.camelKafkaProducerConsumer.routers;

import ir.sobhan.camelKafkaProducerConsumer.service.SumService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class KafkaReceiver extends RouteBuilder {
    final SumService sumService;

    @Override
    public void configure() throws IOException {
        Files.deleteIfExists(Path.of("numbers-log"));

        from("timer:test?period=6000&delay=6000")
                .transform().method("sumService", "getSum")
                .log("sum of values in last 60 seconds : " + "${body}")
                .transform(body().append("\n"))
                .to("file:.?fileName=numbers-log&fileExist=Append");

        from("kafka:topic1")
                .process(sumService);
    }
}
