package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.data.model.ApiKey;
import aliramadhan.assignment.data.repository.ApiKeyRepository;
import aliramadhan.assignment.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    @Autowired
    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public List<ApiKey> getAll() {
        // Return the list of all ApiKey entities from the repository
        return apiKeyRepository.findAll();
    }

    @Override
    public boolean isValidApiKey(String requestApiKey) {
        // Check from the repo
        Optional<ApiKey> apiKeyOpt = apiKeyRepository.findFirstByActiveTrueOrderById();
        if (apiKeyOpt.isPresent()) {
            // Check if it's the same
            String storedApiKey = apiKeyOpt.get().getApiKey();
            return storedApiKey.equals(requestApiKey);
        }
        return false;
    }
}
