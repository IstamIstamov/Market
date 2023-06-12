package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.ReportsDto;
import com.example.simpleProject.model.Reports;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ReportsMapper {
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Reports toEntity(ReportsDto reportsDto);

    public abstract ReportsDto toDto(Reports reports);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateReportsFromDto(ReportsDto reportsDto, @MappingTarget Reports reports);
}
