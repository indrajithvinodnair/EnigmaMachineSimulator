package com.wehrmacht.enigma;

import com.wehrmacht.enigma.config.ApplicationConfigurationProperties;
import com.wehrmacht.enigma.config.CorsLoggingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfigurationProperties.class)
public class EnigmaEncoderDecoderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnigmaEncoderDecoderApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorsLoggingFilter> loggingFilter(){
		FilterRegistrationBean<CorsLoggingFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CorsLoggingFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}
