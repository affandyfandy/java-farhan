package aliramadhan.assignment.mapper;

import aliramadhan.assignment.data.model.StudentBook;
import aliramadhan.assignment.dto.StudentBookDTO;
import aliramadhan.assignment.dto.StudentBookSaveDTO;
import aliramadhan.assignment.dto.StudentBookShowDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentBookMapper {
    StudentBookMapper INSTANCE = Mappers.getMapper(StudentBookMapper.class);

    @Mapping(source = "student.id", target = "studentId")
    StudentBookDTO toStudentBookDTO(StudentBook studentBook);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studentId", target = "student.id")
    StudentBook toStudentBookEntity(StudentBookSaveDTO studentBookSaveDTO);

    @Mapping(target = "id", source = "id")
    StudentBookShowDTO toStudentBookShowDTO(StudentBook studentBook);
}

