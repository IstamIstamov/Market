package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.ImageDto;
import com.example.simpleProject.model.Image;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ImageMapper {
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Image toEntity(ImageDto imageDto);

    public abstract ImageDto toDto(Image image);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateImageFromDto(ImageDto imageDto, @MappingTarget Image image);
}
