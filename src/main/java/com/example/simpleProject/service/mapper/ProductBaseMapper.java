package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.ProductBaseDto;
import com.example.simpleProject.model.ProductBase;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ProductBaseMapper {
    public abstract ProductBase toEntity(ProductBaseDto productBaseDto);

    public abstract ProductBaseDto toDto(ProductBase productBase);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateProductBaseFromDto(ProductBaseDto productBaseDto, @MappingTarget ProductBase productBase);
}
