package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.data.repository.ApiKeyRepository;
import aliramadhan.assignment.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Override
    public boolean isValidApiKey(String apiKey) {
        return apiKeyRepository.findById(apiKey).isPresent();
    }
}
