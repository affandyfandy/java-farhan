package aliramadhan.assignment.controller;

import aliramadhan.assignment.data.model.ApiKey;
import aliramadhan.assignment.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @Autowired
    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @GetMapping
    public ResponseEntity<List<ApiKey>> getAllApiKeys() {
        List<ApiKey> apiKeys = apiKeyService.getAll();
        return ResponseEntity.ok(apiKeys);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateApiKey(@RequestParam String key) {
        boolean isValid = apiKeyService.isValidApiKey(key);
        return ResponseEntity.ok(isValid);
    }
}

