package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.CategoryDto;
import com.example.simpleProject.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    public abstract CategoryDto toDto(Category category);
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Category toEntity(CategoryDto categoryDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateCategoryFromDto(CategoryDto categoryDto,@MappingTarget Category category);
}
