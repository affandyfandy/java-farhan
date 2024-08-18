package aliramadhan.assignment.service;

import aliramadhan.assignment.data.model.ApiKey;

import java.util.List;

public interface ApiKeyService {

    List<ApiKey> getAll();

    // Validates if the provided API key is valid and active
    boolean isValidApiKey(String requestApiKey);
}
