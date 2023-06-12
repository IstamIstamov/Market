package com.example.simpleProject.service.mapper;

import com.example.simpleProject.dto.LoanerDto;
import com.example.simpleProject.model.Loaner;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class LoanerMapper {
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Loaner toEntity(LoanerDto loanerDto);

    public abstract LoanerDto toDto(Loaner loaner);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateLoanerFromDto(LoanerDto loanerDto, @MappingTarget Loaner loaner);
}
