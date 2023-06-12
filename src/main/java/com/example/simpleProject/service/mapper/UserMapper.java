package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.UserDto;
import com.example.simpleProject.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract User toEntity(UserDto userDto);
    public abstract UserDto toDto(User user);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateUserFromDto(UserDto userDto, @MappingTarget User user);
}
