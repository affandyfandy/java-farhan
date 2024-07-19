package aliramadahan.assignment.model;

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

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(length = 14, nullable = false)
    private String firstName;

    @Column(length = 16, nullable = false)
    private String lastName;

    @Column(columnDefinition = "enum('M','F')", nullable = false)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Title> titles;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Salary> salaries;
}
