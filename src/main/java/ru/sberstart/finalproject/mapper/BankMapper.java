package ru.sberstart.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.api.dto.bank.BankCreationDTO;
import ru.sberstart.finalproject.api.dto.bank.BankFullInformationDTO;
import ru.sberstart.finalproject.api.dto.bank.BankInformationDTO;
import ru.sberstart.finalproject.domain.enitity.bank.Bank;

@Mapper(componentModel = "default", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    Bank toEntity(BankCreationDTO creationDTO);

    Bank toEntity(BankInformationDTO bankInformationDTO);

    BankInformationDTO toBankInformationDTO(Bank bank);

    BankFullInformationDTO toBankFullInformationDTO(Bank bank);
}
