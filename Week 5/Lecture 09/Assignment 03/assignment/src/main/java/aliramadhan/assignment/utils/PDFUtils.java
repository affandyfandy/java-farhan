package aliramadhan.assignment.utils;

import java.util.List;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
import java.io.IOException;
import aliramadhan.assignment.model.Employee;
import aliramadhan.assignment.service.EmployeeService;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import java.io.ByteArrayOutputStream;
import java.util.Optional;
import com.itextpdf.html2pdf.HtmlConverter;

@Component
public class PDFUtils {

    private final SpringTemplateEngine templateEngine;
    private final EmployeeService employeeService;

    public PDFUtils(SpringTemplateEngine templateEngine, EmployeeService employeeService) {
        this.templateEngine = templateEngine;
        this.employeeService = employeeService;
    }

    /**
     * Generates a PDF document containing information about a list of employees.
     *
     * @param listEmployees a list of {@link Employee} objects to be included in the
     *                      PDF.
     * @return a byte array representing the generated PDF document.
     * @throws IOException if an error occurs while generating the PDF.
     */
    public byte[] generateEmployeeData(List<Employee> listEmployees) throws IOException {
        // Create a context
        Context context = new Context();

        // Gather the users data needed to generate the file
        Double aveSalary = employeeService.findAverageSalary(); // Average Salary
        Optional<Integer> maxSalary = employeeService.findMaxSalary(); // Max Salary
        Optional<Integer> minSalary = employeeService.findMinSalary(); // Min Salary
        List<String> nameHighSal = employeeService.findEmployeeWithHighestSalary(); // Employee Highest Salary
        List<String> nameLowSal = employeeService.findEmployeeWithLowestSalary(); // Employee Lowest Salary

        // Gather info record and localDate
        int totalRecord = listEmployees.size();
        LocalDate currentDate = LocalDate.now();

        // Binding data to the context pdf
        context.setVariable("customer", "Ali Ramadhan");
        context.setVariable("maxSalary", maxSalary.orElse(0));
        context.setVariable("minSalary", minSalary.orElse(0));
        context.setVariable("aveSalary", aveSalary);
        context.setVariable("employees", listEmployees);
        context.setVariable("record", totalRecord);
        context.setVariable("currentDate", currentDate);
        context.setVariable("nameHighSal", String.join(", ", nameHighSal));
        context.setVariable("nameLowSal", String.join(", ", nameLowSal));

        // Gather the template
        String processedHtml = templateEngine.process("pdf/pdf-template", context);

        // Processing all the bytearrays and ready to send
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(processedHtml, stream);
        stream.flush();
        return stream.toByteArray();
    }
}