package aliramadahan.assignment.service;

import aliramadahan.assignment.model.Title;
import aliramadahan.assignment.model.key.TitleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TitleService {
    List<Title> findAll();
    Page<Title> findAll(Pageable pageable);
    Optional<Title> findById(TitleId id);
    Title updateTitle(Title title);
    Title save(Title title);
    void deleteById(TitleId id);
}
