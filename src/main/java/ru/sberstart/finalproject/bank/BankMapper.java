package ru.sberstart.finalproject.bank;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.bank.api.dto.BankCreationDTO;
import ru.sberstart.finalproject.bank.api.dto.BankFullInformationDTO;
import ru.sberstart.finalproject.bank.api.dto.BankInformationDTO;
import ru.sberstart.finalproject.bank.domain.entity.Bank;

@Mapper(componentModel = "bankService")
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    @Mapping(target = "id", ignore = true)
    Bank toEntity(BankCreationDTO creationDTO);

    @Mapping(target = "id", ignore = true)
    Bank toEntity(BankInformationDTO bankInformationDTO);

    @Mapping(target = "id", ignore = true)
    BankInformationDTO toBankInformationDTO(Bank bank);

    BankFullInformationDTO toBankFullInformationDTO(Bank bank);
}
