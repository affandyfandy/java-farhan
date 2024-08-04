package aliramadhan.assignment.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductShowDTO {
    private Long id;
    private String name;
    private double price;
    private Long category_id;
    private SupplierDTO supplier;
}
