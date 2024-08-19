package aliramadhan.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate enrollmentDate;
}
