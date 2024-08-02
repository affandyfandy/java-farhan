package aliramadhan.assignment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private Long category_id; // Ensure this matches your entity field name
    private Long supplier_id;
}
