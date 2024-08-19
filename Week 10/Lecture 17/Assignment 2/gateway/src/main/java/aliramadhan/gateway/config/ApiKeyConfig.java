package aliramadhan.gateway.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class ApiKeyConfig {
    private String apiKeyHeaderName = "api-key";
    private String authServiceUrl = "http://localhost:8084/api/v1/auth/validate";
}
