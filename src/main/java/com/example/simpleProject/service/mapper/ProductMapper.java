package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.ProductDto;
import com.example.simpleProject.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Product toEntity(ProductDto productDto);

    public abstract ProductDto toDto(Product product);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);
}
