package aliramadahan.assignment.model;

import aliramadahan.assignment.model.key.DeptManagerId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "dept_manager")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DeptManager {

    @EmbeddedId
    private DeptManagerId id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate fromDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate toDate;
}