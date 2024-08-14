package aliramadhan.gateway.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class AuthClient {

    private final WebClient webClient;

    @Autowired
    public AuthClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8084/api/v1/auth").build();
    }


    public Mono<Boolean> validateApiKey(String apiKey) {
        return webClient.get()
                .uri("/validate?key=" + apiKey)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode().is4xxClientError()) {
                        return Mono.just(false);
                    }
                    return Mono.error(ex);
                });
    }
}