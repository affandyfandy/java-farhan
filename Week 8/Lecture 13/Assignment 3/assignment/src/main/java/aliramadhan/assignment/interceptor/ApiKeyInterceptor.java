package aliramadhan.assignment.interceptor;

import aliramadhan.assignment.data.model.ApiKey;
import aliramadhan.assignment.data.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("api-key");
        if (apiKey == null || !apiKeyRepository.existsById(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        ApiKey key = apiKeyRepository.findById(apiKey).orElseThrow();
        key.setLastUsed(LocalDateTime.now());
        apiKeyRepository.save(key);

        // Add username to the header
        response.setHeader("username", key.getUsername());
        return true;
    }
}
