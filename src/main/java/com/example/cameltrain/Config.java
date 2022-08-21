package com.example.cameltrain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class Config {

    @Bean("genRandom")
    Supplier<String> randomConfig() {
        return () -> String.valueOf(Math.ceil(Math.random() * 100 + 1));
    }
}
