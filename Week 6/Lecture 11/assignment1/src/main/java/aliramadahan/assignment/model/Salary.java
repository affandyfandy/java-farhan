package aliramadahan.assignment.model;

import aliramadahan.assignment.model.key.SalaryId;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "salaries")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Salary implements Serializable {

    @EmbeddedId
    private SalaryId id;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

}
