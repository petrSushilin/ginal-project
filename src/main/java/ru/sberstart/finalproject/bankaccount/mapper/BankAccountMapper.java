package ru.sberstart.finalproject.bankaccount.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountActiveInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountCreationDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountDeactivateInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountFullInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountStateDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountSuccessTransactionDTO;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;

@Mapper(componentModel = "bankAccountService")
public interface BankAccountMapper {
    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "state", ignore = true)
    BankAccount toEntity(BankAccountCreationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "balance", ignore = true)
    BankAccount toEntity(BankAccountStateDTO dto);

    @Mapping(target = "id", ignore = true)
    BankAccountFullInformationResponseDTO toBankAccountFullInformationResponseDTO(BankAccount bankAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "state", ignore = true)
    BankAccountActiveInformationResponseDTO toBankAccountActiveInformationResponseDTO(BankAccount bankAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "balance", ignore = true)
    BankAccountDeactivateInformationResponseDTO toBankAccountDeactivateInformationResponseDTO(BankAccount bankAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "state", ignore = true)
    BankAccountSuccessTransactionDTO toBankAccountSuccessTransactionDTO(BankAccount bankAccount);
}
