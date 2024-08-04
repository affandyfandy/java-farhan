package aliramadhan.assignment.service;

import aliramadhan.assignment.dto.SupplierDTO;
import aliramadhan.assignment.dto.SupplierSaveDTO;
import aliramadhan.assignment.dto.SupplierShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {

    SupplierDTO saveSupplier(SupplierSaveDTO supplierSaveDTO);

    SupplierShowDTO getSupplierById(Long id);

    Page<SupplierShowDTO> getAllSuppliers(Pageable pageable);

    SupplierDTO updateSupplier(Long id, SupplierSaveDTO supplierSaveDTO);

    void deleteSupplier(Long id);
}
