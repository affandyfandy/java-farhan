package aliramadhan.assignment.mapper;

import aliramadhan.assignment.dto.SupplierDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierDTO toSupplierDTO(Long supplierId);
}


