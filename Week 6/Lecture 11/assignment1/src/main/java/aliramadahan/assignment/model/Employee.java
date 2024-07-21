package aliramadahan.assignment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empNo;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(length = 14, nullable = false)
    private String firstName;

    @Column(length = 16, nullable = false)
    private String lastName;

    @Column(columnDefinition = "enum('M','F')", nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Title> titles;
}
