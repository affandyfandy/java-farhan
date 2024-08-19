package aliramadhan.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBookShowDTO {
    private String id;
    private StudentDTO student;
    private BookDTO book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
