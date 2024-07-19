package aliramadahan.assignment.controller;

import aliramadahan.assignment.model.Salary;
import aliramadahan.assignment.model.key.SalaryId;
import aliramadahan.assignment.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/salaries")
public class SalaryEmployeeController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public ResponseEntity<List<Salary>> getAllSalaries() {
        List<Salary> salaries = salaryService.findAll();
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Salary>> getAllSalaries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "toDate") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Pageable pageable = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Salary> salaries = salaryService.findAll(pageable);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    @GetMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Salary> getSalaryById(
            @PathVariable Integer empNo,
            @PathVariable String fromDate) {
        try {
            LocalDate fromDateParsed = LocalDate.parse(fromDate);
            SalaryId id = new SalaryId(empNo, fromDateParsed);

            Optional<Salary> salary = salaryService.findById(id);
            return salary.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            return ResponseEntity.badRequest().build();
        }
    }

//    @PostMapping
//    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
//        Salary createdSalary = salaryService.save(salary);
//        return new ResponseEntity<>(createdSalary, HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        // Create new salary record
        Salary newSalary = salaryService.save(salary);
        return new ResponseEntity<>(newSalary, HttpStatus.CREATED);
    }


    @PutMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Salary> updateSalary(
            @PathVariable Integer empNo,
            @PathVariable String fromDate,
            @RequestBody Salary salary) {
        SalaryId salaryId = new SalaryId(empNo, LocalDate.parse(fromDate));
        salary.setId(salaryId);
        Salary updatedSalary = salaryService.updateSalary(salary);
        return new ResponseEntity<>(updatedSalary, HttpStatus.OK);
    }

    @DeleteMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Void> deleteSalary(
            @PathVariable Integer empNo,
            @PathVariable String fromDate) {
        SalaryId salaryId = new SalaryId(empNo, LocalDate.parse(fromDate));
        salaryService.deleteById(salaryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
