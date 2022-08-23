package ir.sobhan.camelKafkaProducerConsumer.service;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = SumService.class)
public class SumServiceTest {

    @Autowired
    SumService sumService;

    @MockBean
    Exchange exchange;

    @MockBean
    Message message;

    @Test
    void sumServiceTest() {
        double sum = 0D;

        for (double i = 0; i < 10; i++) {

            Mockito.when(exchange.getIn()).thenReturn(message);
            Mockito.when(message.getBody()).thenReturn(String.valueOf(i));

            sumService.process(exchange);
            sum += i;
        }

        Assertions.assertEquals(String.valueOf(sum), sumService.getSum());
    }
}
