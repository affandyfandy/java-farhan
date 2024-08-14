package aliramadhan.gateway.config;

import aliramadhan.gateway.ApiKeyGatewayFilterFactory;
import aliramadhan.gateway.client.AuthClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public ApiKeyGatewayFilterFactory apiKeyGatewayFilterFactory(AuthClient authClient) {
        return new ApiKeyGatewayFilterFactory(authClient);
    }
}

