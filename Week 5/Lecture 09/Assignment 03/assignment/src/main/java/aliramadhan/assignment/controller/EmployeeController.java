package aliramadhan.assignment.controller;

import aliramadhan.assignment.model.Employee;
import aliramadhan.assignment.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.lowagie.text.DocumentException;
import aliramadhan.assignment.utils.PDFUtils;
import java.io.IOException;
import java.util.List;

// import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PDFUtils pdfUtils;

    // Mapping to list all employees
    // @GetMapping("/list")
    // public String listEmployees(Model model) {
    // List<Employee> employees = employeeService.getAllEmployees();
    // model.addAttribute("employees", employees);
    // return "employees/list-employees";
    // }
    // @GetMapping("/list")
    // public String listEmployees(Model theModel, @RequestParam(defaultValue = "0")
    // int page) {
    // Pageable pageable = PageRequest.of(page, 20); // 20 items per page
    // Page<Employee> employeePage = employeeService.getAllEmployees(pageable);

    // theModel.addAttribute("employees", employeePage);
    // return "employees/list-employees";
    // }
    @GetMapping("/list")
    public String listEmployees(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10; // Define the number of employees per page
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Employee> employeePage = employeeService.getAllEmployees(pageable);

        model.addAttribute("employeePage", employeePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", Math.max(employeePage.getTotalPages(), 1));

        // Calculate the range of page numbers to display
        int totalPages = Math.max(employeePage.getTotalPages(), 1);
        int startPage = Math.max(0, Math.min(page - 5, totalPages - 10));
        int endPage = Math.min(startPage + 10, totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "employees/list-employees";
    }

    // Mapping to show the form for adding a new employee
    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/create";
    }

    // Handling the form submission to save a new employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees/list"; // Redirect to the list page after saving
    }

    // Mapping to show the form for editing an existing employee
    @GetMapping("/edit")
    public String showFormForUpdate(@RequestParam("employeeId") String id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/edit";
    }

    // Mapping to delete an employee
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") String id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees/list"; // Redirect to the list page after deleting
    }

    @PostMapping("/upload")
    public String uploadCsvFile(@RequestParam("file") MultipartFile file) {
        // Upload the CSV using the service
        employeeService.uploadCsv(file);

        // Redirect to /employees/list
        return "redirect:/employees/list";
    }

    @PostMapping("/pdf")
    public ResponseEntity<?> downloadPDF() throws IOException, DocumentException {
        try {
            // Get the existing employee
            List<Employee> employees = employeeService.findAll();

            // Convert to PDF bytes
            byte[] pdfBytes = pdfUtils.generateEmployeeData(employees);

            // Set headers for PDF download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("employees.pdf").build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (IOException e) {
            // Handle exception appropriately (e.g., show error message)
            return ResponseEntity.badRequest().build();
        }
    }
}
