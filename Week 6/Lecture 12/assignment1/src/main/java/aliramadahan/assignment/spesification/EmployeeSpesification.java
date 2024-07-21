package aliramadahan.assignment.spesification;

import aliramadahan.assignment.model.Employee;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class EmployeeSpesification {

    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, builder) -> firstName == null ?
                builder.conjunction() : builder.equal(root.get("firstName"), firstName);
    }

    public static Specification<Employee> hasLastName(String lastName) {
        return (root, query, builder) -> lastName == null ?
                builder.conjunction() : builder.equal(root.get("lastName"), lastName);
    }

    public static Specification<Employee> hasGender(String gender) {
        return (root, query, builder) -> gender == null ?
                builder.conjunction() : builder.equal(root.get("gender"), gender);
    }

    public static Specification<Employee> hasHireDate(LocalDate hireDate) {
        return (root, query, builder) -> hireDate == null ?
                builder.conjunction() : builder.equal(root.get("hireDate"), hireDate);
    }

    public static Specification<Employee> hasBirthDate(LocalDate birthDate) {
        return (root, query, builder) -> birthDate == null ?
                builder.conjunction() : builder.equal(root.get("birthDate"), birthDate);
    }
}
