package ru.sberstart.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.api.dto.bankaccount.inner.BankAccountCardIssueInformationDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.inner.BankAccountFullInformationDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountCreationRequestDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountActiveInformationResponseDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountDeactivateInformationResponseDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountCreationResponseDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountInformationResponseDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountStateRequestDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountSuccessTransactionResponseDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Mapper(componentModel = "default", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankAccountMapper {
    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccount toEntity(BankAccountCreationRequestDTO dto);

    BankAccount toEntity(BankAccountStateRequestDTO dto);

    BankAccountCreationResponseDTO toBankAccountFullInformationResponseDTO(BankAccount bankAccount);

    BankAccountActiveInformationResponseDTO toBankAccountActiveInformationResponseDTO(BankAccount bankAccount);

    BankAccountDeactivateInformationResponseDTO toBankAccountDeactivateInformationResponseDTO(BankAccount bankAccount);

    BankAccountSuccessTransactionResponseDTO toBankAccountSuccessTransactionDTO(BankAccount bankAccount);

    BankAccountInformationResponseDTO toBankAccountInformationResponseDTO(BankAccountFullInformationDTO bankAccountFullInformationDTO);

    BankAccountCardIssueInformationDTO toBankAccountCardIssueInformationDTO(BankAccountFullInformationDTO bankAccountInformation);
}
