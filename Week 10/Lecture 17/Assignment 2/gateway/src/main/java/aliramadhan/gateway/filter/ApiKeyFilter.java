package aliramadhan.gateway.filter;

import aliramadhan.gateway.config.ApiKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ApiKeyFilter extends AbstractGatewayFilterFactory<ApiKeyConfig> {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public ApiKeyFilter(WebClient.Builder webClientBuilder) {
        super(ApiKeyConfig.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(ApiKeyConfig config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String apiKey = request.getHeaders().getFirst(config.getApiKeyHeaderName());

            if (apiKey == null || apiKey.isEmpty()) {
                return onError(exchange, "API key is missing");
            }

            return webClientBuilder.build()
                    .get()
                    .uri(config.getAuthServiceUrl() + "?key=" + apiKey)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .flatMap(isValid -> isValid ? chain.filter(exchange) : onError(exchange, "Invalid API key"));
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errorMsg) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorMsg.getBytes())));
    }
}
