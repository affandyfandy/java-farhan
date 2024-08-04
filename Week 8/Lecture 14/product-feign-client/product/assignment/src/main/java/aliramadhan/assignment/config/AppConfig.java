package aliramadhan.assignment.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;  // Set the logging level for Feign clients
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Add any custom headers or modify the request here
            requestTemplate.header("Header", "FPT SOFTWARE");
        };
    }

    // Example of basic authentication if needed
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }
}
