package aliramadahan.assignment.service.serviceImpl;

import aliramadahan.assignment.model.Title;
import aliramadahan.assignment.model.key.TitleId;
import aliramadahan.assignment.repository.TitleRepository;
import aliramadahan.assignment.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    @Override
    public Page<Title> findAll(Pageable pageable) {
        return titleRepository.findAll(pageable);
    }

    @Override
    public Optional<Title> findById(TitleId id) {
        return titleRepository.findById(id);
    }

    @Override
    public Title save(Title title) {
        return titleRepository.save(title);
    }

    @Override
    public void deleteById(TitleId id) {
        titleRepository.deleteById(id);
    }

    @Override
    public Title updateTitle(Title title) {
        return titleRepository.save(title);
    }

}
