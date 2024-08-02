package aliramadhan.assignment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveDTO {
    private String name;
    private double price;
    private Long category_id;
    private Long supplier_id;
}