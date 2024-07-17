package aliramadahan.assignment.model.key;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
public class DeptManagerId implements Serializable {
    private Integer empNo;
    private String deptNo;
}
