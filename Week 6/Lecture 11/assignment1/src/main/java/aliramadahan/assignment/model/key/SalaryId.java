package aliramadahan.assignment.model.key;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalaryId implements Serializable{
    @Column(name="emp_no")
    private Integer empNo;
    @Column(name = "from_date")
    private LocalDate fromDate;
}