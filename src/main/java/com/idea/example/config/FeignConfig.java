package com.idea.example.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.httpclient.ApacheHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableFeignClients(basePackages = {"com.idea.example.provider.feign"})
public class FeignConfig {

    /* 预配
        1. Feign client
        2. Feign client: org.apache.http.impl.client.HttpClientBuilder@xx
        3. Feign feignBuilder
        4. Feign Logger.Level
        5. Feign Decoder
        6. Feign createGson
        7. Feign Encoder
        8. Feign createGson
       执行ticker()
        9. ticker
    */
    @Bean
    public Client client() {

        log.info("-------------Feign client");

        final HttpClientBuilder builder = HttpClientBuilder.create()
                .setMaxConnPerRoute(300)
                .setMaxConnTotal(300);

        log.info("-------------Feign Client: {}", builder);

        return new ApacheHttpClient(builder.build());
    }

    @Bean
    public Feign.Builder feignBuilder() {

        log.info("-------------Feign feignBuilder");

        return Feign.builder()
                .client(client());
    }

    @Bean
    Logger.Level feignLoggerLevel() {

        log.info("-------------Feign Logger.Level");

        return Logger.Level.FULL;
    }

    @Bean
    public Decoder decoder() {

        log.info("-------------Feign Decoder");

        return new GsonDecoder(createGson());//返回空数组
    }

    @Bean
    public Encoder encoder() {

        log.info("-------------Feign Encoder");

        return new GsonEncoder(createGson());//返回空数组
    }

    private Gson createGson() {

        log.info("-------------Feign createGson");

        return new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();
    }

}
