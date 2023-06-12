package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.ForeignDebtDto;
import com.example.simpleProject.model.ForeignDebt;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ForeignDebtMapper {
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract ForeignDebt toEntity(ForeignDebtDto foreignDebtDto);

    public abstract ForeignDebtDto toDto(ForeignDebt foreignDebt);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateForeignFromDto(ForeignDebtDto foreignDebtDto, @MappingTarget ForeignDebt foreignDebt);
}
