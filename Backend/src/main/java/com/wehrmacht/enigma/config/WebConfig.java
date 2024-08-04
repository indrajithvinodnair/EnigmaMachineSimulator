package com.wehrmacht.enigma.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private ApplicationConfigurationProperties applicationConfigurationProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("CORS METHOD INVOKED : "+applicationConfigurationProperties.getAppFrontendUrl());
        registry.addMapping("/**")
                .allowedOrigins(applicationConfigurationProperties.getAppFrontendUrl())
                .allowedMethods("POST","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}


