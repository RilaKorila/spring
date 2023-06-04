package com.example.sample1app;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SampleConfigure {
    @Bean
    public Post post(){
        // returnするインスタンスをbeanとして登録
        return new Post(0, 0, "dummy", "ダミーだよ");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
