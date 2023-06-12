package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.BasketDto;
import com.example.simpleProject.model.Basket;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class BasketMapper {
    public abstract BasketDto toDto(Basket basket);
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Basket toEntity(BasketDto basketDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateBasketFromDto(BasketDto basketDto,@MappingTarget Basket basket);
}
