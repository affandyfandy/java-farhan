package aliramadhan.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookShowDTO {
    private String id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String genre;
    private Integer availableCopies;
}

