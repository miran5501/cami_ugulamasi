package com.cami_rehberi.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // tüm endpointler
                        .allowedOrigins("*") // tüm domainlere izin
                        .allowedMethods("*") // GET, POST, PUT, DELETE, OPTIONS hepsi
                        .allowedHeaders("*") // tüm headerlara izin
                        .allowCredentials(false); // "*" ile birlikte true olamaz, false olmalı
            }
        };
    }
}
