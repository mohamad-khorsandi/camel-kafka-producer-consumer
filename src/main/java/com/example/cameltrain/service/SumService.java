package com.example.cameltrain.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service("sumService")
public class SumService implements Processor {
    private AtomicReference<Double> sum = new AtomicReference<>(0D);

    @Override
    public void process(Exchange exchange) {
        double num = Double.parseDouble((String) exchange.getIn().getBody());
        sum.set(sum.get() + num);
    }

    public String getSum() {
        double tmp = sum.get();
        sum.set(0D);
        return Double.toString(tmp);
    }
}
