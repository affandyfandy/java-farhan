package aliramadhan.assignment.client;

import aliramadhan.assignment.config.AppConfig;
import aliramadhan.assignment.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "books-services", url = "http://localhost:8087/api/v1", configuration = AppConfig.class)
public interface BookClient {

    @GetMapping("/books/{id}")
    BookDTO getBookById(@PathVariable("id") String id);

    @PutMapping("/books/{id}/reduce-copies")
    String reduceAvailableCopies(
            @PathVariable String id,
            @RequestParam Integer quantity);


}
