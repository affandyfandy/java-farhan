package aliramadhan.assignment.client;

import aliramadhan.assignment.config.AppConfig;
import aliramadhan.assignment.dto.SupplierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="suppliers-services", url="http://localhost:8085/api/v1", configuration = AppConfig.class)
public interface SupplierClient {
    @GetMapping("/suppliers/{id}")
    SupplierDTO getSupplierById(@PathVariable("id") Long id);
}
