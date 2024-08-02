package aliramadhan.assignment.service.impl;


import aliramadhan.assignment.data.model.Supplier;
import aliramadhan.assignment.data.repository.SupplierRepository;
import aliramadhan.assignment.dto.SupplierDTO;
import aliramadhan.assignment.dto.SupplierSaveDTO;
import aliramadhan.assignment.dto.SupplierShowDTO;
import aliramadhan.assignment.mapper.SupplierMapper;
import aliramadhan.assignment.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    SupplierMapper supplierMapper;

    @Override
    public SupplierDTO saveSupplier(SupplierSaveDTO supplierSaveDTO) {
        Supplier supplier = supplierMapper.toSupplier(supplierSaveDTO);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toSupplierDTO(supplier);
    }

    @Override
    public SupplierShowDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
        return supplierMapper.toShowDTO(supplier);
    }

    @Override
    public Page<SupplierShowDTO> getAllSuppliers(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<Supplier> suppliers = supplierRepository.findAll(sortedPageable);
        return suppliers.map(supplierMapper::toShowDTO);
    }


    @Override
    public SupplierDTO updateSupplier(Long id, SupplierSaveDTO supplierSaveDTO) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        supplier.setName(supplierSaveDTO.getName());
        supplier.setAddress(supplierSaveDTO.getAddress());
        supplier.setContactInfo(supplier.getContactInfo());
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toSupplierDTO(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}