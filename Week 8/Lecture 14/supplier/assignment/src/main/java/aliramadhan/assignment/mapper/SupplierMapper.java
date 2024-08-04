package aliramadhan.assignment.mapper;

import aliramadhan.assignment.data.model.Supplier;
import aliramadhan.assignment.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    // Supplier - SupplierDTO
    SupplierDTO toSupplierDTO(Supplier supplier);

    // Supplier - SupplierShowDTO
    SupplierShowDTO toShowDTO(Supplier supplier);

    // Supplier - SupplierSaveDTO
    Supplier toSupplier(SupplierSaveDTO supplierSaveDTO);
}