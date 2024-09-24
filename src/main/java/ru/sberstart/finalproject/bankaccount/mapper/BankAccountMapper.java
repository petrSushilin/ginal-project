package ru.sberstart.finalproject.bankaccount.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.bankaccount.api.dto.inner.BankAccountCardIssueInformationDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.inner.BankAccountFullInformationDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountCreationRequestDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountActiveInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountDeactivateInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountCreationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountStateRequestDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountSuccessTransactionResponseDTO;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Mapper(componentModel = "bankAccountService")
public interface BankAccountMapper {
    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "state", ignore = true)
    BankAccount toEntity(BankAccountCreationRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "balance", ignore = true)
    BankAccount toEntity(BankAccountStateRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    BankAccountCreationResponseDTO toBankAccountFullInformationResponseDTO(BankAccount bankAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "state", ignore = true)
    BankAccountActiveInformationResponseDTO toBankAccountActiveInformationResponseDTO(BankAccount bankAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "balance", ignore = true)
    BankAccountDeactivateInformationResponseDTO toBankAccountDeactivateInformationResponseDTO(BankAccount bankAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "state", ignore = true)
    BankAccountSuccessTransactionResponseDTO toBankAccountSuccessTransactionDTO(BankAccount bankAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    BankAccountInformationResponseDTO toBankAccountInformationResponseDTO(BankAccountFullInformationDTO bankAccountFullInformationDTO);

    @Mapping(target = "bankName", ignore = true)
    @Mapping(target = "bankIdentityNumber", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "birthdate", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "passportNumber", ignore = true)
    @Mapping(target = "registryDate", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "balance", ignore = true)
    BankAccountCardIssueInformationDTO toBankAccountCardIssueInformationDTO(BankAccountFullInformationDTO bankAccountInformation);
}
