package aliramadhan.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBookSaveDTO {
    private String studentId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
