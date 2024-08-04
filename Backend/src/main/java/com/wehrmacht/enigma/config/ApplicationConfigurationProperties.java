package com.wehrmacht.enigma.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "enigma")
public class ApplicationConfigurationProperties {
    String appFrontendUrl;

    public String getAppFrontendUrl() {
        return appFrontendUrl;
    }

    public void setAppFrontendUrl(String appFrontendUrl) {
        this.appFrontendUrl = appFrontendUrl;
    }
}
