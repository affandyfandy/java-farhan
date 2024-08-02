package aliramadhan.assignment.mapper;

import aliramadhan.assignment.data.model.Product;
import aliramadhan.assignment.dto.ProductDTO;
import aliramadhan.assignment.dto.ProductSaveDTO;
import aliramadhan.assignment.dto.ProductShowDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "categoryId", target = "category_id")
    @Mapping(source = "supplierId", target = "supplier_id")
    ProductDTO toProductDTO(Product product);

    @Mapping(source = "categoryId", target = "category_id")
    @Mapping(source = "supplierId", target = "supplier")
    ProductShowDTO toShowDTO(Product product);

    @Mapping(source = "category_id", target = "categoryId")
    @Mapping(source = "supplier_id", target = "supplierId")
    Product toProduct(ProductSaveDTO productSaveDTO);
}
