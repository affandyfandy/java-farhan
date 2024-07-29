// Model
package aliramadhan.assignment.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "department")
    private String department;

    @Column(name = "position")
    private String position;

    @Min(value = 0, message = "Salary must be a positive number")
    @Column(name = "salary")
    private double salary;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^\\+62[0-9]{9,12}$", message = "Phone number must be a valid Indonesian number starting with +62 and followed by 9 to 12 digits")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "Date of birth is mandatory")
    @Column(name = "dob")
    private LocalDate dob;
}