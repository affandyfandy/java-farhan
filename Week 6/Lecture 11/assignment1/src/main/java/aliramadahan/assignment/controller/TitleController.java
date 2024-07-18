package aliramadahan.assignment.controller;

import aliramadahan.assignment.model.Title;
import aliramadahan.assignment.model.key.TitleId;
import aliramadahan.assignment.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping
    public ResponseEntity<List<Title>> getAllTitles() {
        List<Title> titles = titleService.findAll();
        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

//    @GetMapping("/paged")
//    public ResponseEntity<Page<Title>> getAllTitles(Pageable pageable) {
//        Page<Title> titles = titleService.findAll(pageable);
//        return new ResponseEntity<>(titles, HttpStatus.OK);
//    }
    @GetMapping("/page")
    public ResponseEntity<Page<Title>> getPagedTitles(
            @PageableDefault(size = 10, sort = "id.empNo", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            Page<Title> titles = titleService.findAll(pageable);
            return ResponseEntity.ok(titles);
        } catch (Exception e) {
//                logger.error("Error getting paged titles", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<Title> getTitleById(
            @PathVariable Integer empNo,
            @PathVariable String title,
            @PathVariable String fromDate) {
        try {
            LocalDate fromDateParsed = LocalDate.parse(fromDate);
            TitleId id = new TitleId(empNo, title, fromDateParsed);
            Optional<Title> titleOptional = titleService.findById(id);

            return titleOptional.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Title> createTitle(@RequestBody Title title) {
        Title savedTitle = titleService.save(title);
        return new ResponseEntity<>(savedTitle, HttpStatus.CREATED);
    }

//    @PutMapping("/{empNo}/{title}/{fromDate}")
//    public ResponseEntity<Title> updateTitle(
//            @PathVariable Integer empNo,
//            @PathVariable String title,
//            @PathVariable String fromDate,
//            @RequestBody Title titleDetails) {
//        try {
//            LocalDate fromDateParsed = LocalDate.parse(fromDate);
//            TitleId id = new TitleId(empNo, title, fromDateParsed);
//            Optional<Title> titleOptional = titleService.findById(id);
//
//            if (titleOptional.isPresent()) {
//                Title titleToUpdate = titleOptional.get();
//                titleToUpdate.setToDate(titleDetails.getToDate());
//                Title updatedTitle = titleService.updateTitle(titleToUpdate);
//                return ResponseEntity.ok(updatedTitle);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    @PutMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<Title> updateTitle(
            @PathVariable Integer empNo,
            @PathVariable String title,
            @PathVariable String fromDate,
            @RequestBody Title titleDetails) {
        try {
            LocalDate fromDateParsed = LocalDate.parse(fromDate);
            TitleId oldId = new TitleId(empNo, title, fromDateParsed);
            Optional<Title> titleOptional = titleService.findById(oldId);

            if (titleOptional.isPresent()) {
                Title titleToUpdate = titleOptional.get();

                // Update the TitleId
                TitleId newId = titleDetails.getId();
                if (!oldId.equals(newId)) {
                    titleService.deleteById(oldId); // Remove the old entry
                    titleToUpdate.setId(newId); // Set new composite key
                }

                // Update other fields
                titleToUpdate.setToDate(titleDetails.getToDate());

                Title updatedTitle = titleService.updateTitle(titleToUpdate);
                return ResponseEntity.ok(updatedTitle);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitleById(@PathVariable TitleId id) {
        titleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
