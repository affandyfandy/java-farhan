package aliramadhan.assignment.mapper;

import aliramadhan.assignment.dto.ProductDTO;
import aliramadhan.assignment.dto.ProductSaveDTO;
import aliramadhan.assignment.dto.ProductShowDTO;
import aliramadhan.assignment.data.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Product - ProductDTO
    ProductDTO toProductDTO(Product product);

    // Product - ProductShowDTO
    ProductShowDTO toShowDTO(Product product);

    // Product - ProductSaveDTO
    Product toProduct(ProductSaveDTO productSaveDTO);
}

