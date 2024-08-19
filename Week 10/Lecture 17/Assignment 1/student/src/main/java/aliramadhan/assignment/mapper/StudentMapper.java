package aliramadhan.assignment.mapper;

import aliramadhan.assignment.data.model.Student;
import aliramadhan.assignment.dto.StudentDTO;
import aliramadhan.assignment.dto.StudentSaveDTO;
import aliramadhan.assignment.dto.StudentShowDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO toStudentDTO(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Student toStudentEntity(StudentSaveDTO studentSaveDTO);

    @Mapping(target = "id", source = "id")
    StudentShowDTO toStudentShowDTO(Student student);
}
