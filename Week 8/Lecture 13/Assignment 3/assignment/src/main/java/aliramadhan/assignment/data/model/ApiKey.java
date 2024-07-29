package aliramadhan.assignment.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_key2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey {

    @Id
    @Column(name = "apkey")
    private String key;
    @Column(name = "username")
    private String username;
    @Column(name = "last_used")
    private LocalDateTime lastUsed;

    // Getters and setters
}

