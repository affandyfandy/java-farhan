package aliramadahan.assignment.model;

import java.time.LocalDate;

import aliramadahan.assignment.model.key.TitleId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "titles")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Title {

    @EmbeddedId
    private TitleId id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "to_date")
    private LocalDate toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empNo")
    @JoinColumn(name = "emp_no")
    @JsonBackReference
    private Employee employee;
}