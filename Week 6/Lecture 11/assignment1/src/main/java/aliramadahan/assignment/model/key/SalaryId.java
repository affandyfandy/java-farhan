package aliramadahan.assignment.model.key;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SalaryId implements Serializable{
    private Integer employee;
    private Date fromDate;
}