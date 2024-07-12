package aliramadhan.assignment.dto;

import java.time.LocalDate;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class EmployeeDTO {
    private String id;
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    private String name;

    private int age;

    private String department;

    private String position;

    @Min(value = 0, message = "Salary must be a positive number")
    private double salary;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "^\\+62[0-9]{9,12}$", message = "Phone number must be a valid Indonesian number starting with +62 and followed by 9 to 12 digits")
    private String phoneNumber;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;
}
