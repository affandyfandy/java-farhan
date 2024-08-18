package aliramadhan.gateway.filter;

import aliramadhan.gateway.config.ApiKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ApiKeyFilter implements GlobalFilter, Ordered {

    private final WebClient.Builder webClientBuilder;
    private final ApiKeyConfig apiKeyConfig;

    @Autowired
    public ApiKeyFilter(WebClient.Builder webClientBuilder, ApiKeyConfig apiKeyConfig) {
        this.webClientBuilder = webClientBuilder;
        this.apiKeyConfig = apiKeyConfig;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String apiKey = request.getHeaders().getFirst(apiKeyConfig.getApiKeyHeaderName());

        if (apiKey == null || apiKey.isEmpty()) {
            return onError(exchange, "API key is missing");
        }

        return webClientBuilder.build()
                .get()
                .uri(apiKeyConfig.getAuthServiceUrl() + "?key=" + apiKey)
                .retrieve()
                .bodyToMono(Boolean.class)
                .flatMap(isValid -> isValid ? chain.filter(exchange) : onError(exchange, "Invalid API key"));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errorMsg) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorMsg.getBytes())));
    }

    @Override
    public int getOrder() {
        return -1; // Ensures this filter is applied early in the filter chain
    }
}
