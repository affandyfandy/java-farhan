package aliramadhan.assignment.controller;

import aliramadhan.assignment.dto.*;
import aliramadhan.assignment.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierDTO> saveSupplier(@RequestBody SupplierSaveDTO supplierSaveDTO) {
        SupplierDTO supplierDTO = supplierService.saveSupplier(supplierSaveDTO);
        return ResponseEntity.ok().body(supplierDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierShowDTO> getSupplierById(@PathVariable Long id) {
        SupplierShowDTO supplierShowDTO = supplierService.getSupplierById(id);
        return ResponseEntity.ok().body(supplierShowDTO);
    }

    @GetMapping
    public ResponseEntity<Page<SupplierShowDTO>> getAllSuppliers(Pageable pageable) {
        Page<SupplierShowDTO> categories = supplierService.getAllSuppliers(pageable);
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateProduct(@PathVariable Long id, @RequestBody SupplierSaveDTO supplierSaveDTO) {
        SupplierDTO productDTO = supplierService.updateSupplier(id, supplierSaveDTO);
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}