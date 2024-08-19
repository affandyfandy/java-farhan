package aliramadhan.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentShowDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate enrollmentDate;
    private List<StudentBookDTO> studentBooks;
}

