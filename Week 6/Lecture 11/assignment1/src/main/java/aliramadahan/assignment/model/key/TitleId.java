package aliramadahan.assignment.model.key;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TitleId implements Serializable {
    private Integer empNo;
    private String title;
    @Column(name = "from_date")
    private LocalDate fromDate;
}
