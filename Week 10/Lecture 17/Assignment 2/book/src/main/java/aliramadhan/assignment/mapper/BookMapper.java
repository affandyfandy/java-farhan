package aliramadhan.assignment.mapper;

import aliramadhan.assignment.data.model.Book;
import aliramadhan.assignment.dto.BookDTO;
import aliramadhan.assignment.dto.BookSaveDTO;
import aliramadhan.assignment.dto.BookShowDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
    BookDTO toBookDTO(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Book toBookEntity(BookSaveDTO bookSaveDTO);

    @Mapping(target = "id", source = "id")
    BookShowDTO toBookShowDTO(Book book);
}

