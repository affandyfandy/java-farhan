package aliramadhan.assignment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import aliramadhan.assignment.dto.EmployeeDTO;
import aliramadhan.assignment.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    // Mapper to Employee DTO
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "age", target = "age")
//    @Mapping(source = "department", target = "department")
//    @Mapping(source = "position", target = "position")
//    @Mapping(source = "salary", target = "salary")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "phoneNumber", target = "phoneNumber")
//    @Mapping(source = "dob", target = "dob")
    EmployeeDTO toDTO(Employee employee);

    // Mapper to Employee model
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "age", target = "age")
//    @Mapping(source = "department", target = "department")
//    @Mapping(source = "position", target = "position")
//    @Mapping(source = "salary", target = "salary")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "phoneNumber", target = "phoneNumber")
//    @Mapping(source = "dob", target = "dob")
    Employee toEntity(EmployeeDTO employeeDTO);
}