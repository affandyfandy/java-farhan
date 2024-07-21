package aliramadahan.assignment.model.key;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class DeptEmpId implements Serializable {
    private Integer empNo;
    private String deptNo;
}