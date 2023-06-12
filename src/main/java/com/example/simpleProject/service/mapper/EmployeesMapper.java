package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.EmployeesDto;
import com.example.simpleProject.model.Employees;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring", imports = Collectors.class)
@RequiredArgsConstructor
public abstract class EmployeesMapper {
    @Lazy
    @Autowired
    protected UserMapper userMapper;

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Employees toEntity(EmployeesDto employeesDto);
    public abstract EmployeesDto toDto(Employees employees);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEmployeesFromDto(EmployeesDto employeesDto, @MappingTarget Employees employees);
}
