package aliramadahan.assignment.model;

import aliramadahan.assignment.model.key.DeptEmpId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "dept_emp")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DeptEmployee {

    @EmbeddedId
    private DeptEmpId id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate fromDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate toDate;
}
