package aliramadhan.assignment.mapper;

import aliramadhan.assignment.data.model.Category;
import aliramadhan.assignment.data.model.Product;
import aliramadhan.assignment.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toCategoryDTO(Category category);

    CategoryShowDTO toShowDTO(Category category);

    Category toCategory(CategorySaveDTO categorySaveDTO);

}