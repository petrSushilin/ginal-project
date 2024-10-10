package ru.sberstart.finalproject.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import ru.sberstart.finalproject.api.dto.bankaccount.inner.BankAccountCardIssueInformationDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.inner.BankAccountFullInformationDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountCreationRequestDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountStateRequestDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountActiveInformationResponseDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountCreationResponseDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountDeactivateInformationResponseDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountInformationResponseDTO;
import ru.sberstart.finalproject.api.dto.bankaccount.response.BankAccountSuccessTransactionResponseDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T08:57:06+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
public class BankAccountMapperImpl implements BankAccountMapper {

    @Override
    public BankAccount toEntity(BankAccountCreationRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UUID bankId = null;
        UUID userId = null;

        bankId = dto.bankId();
        userId = dto.userId();

        UUID id = null;
        LocalDate registryDate = null;
        String number = null;
        BigDecimal balance = null;
        BankAccountStates state = null;

        BankAccount bankAccount = new BankAccount( id, bankId, userId, registryDate, number, balance, state );

        return bankAccount;
    }

    @Override
    public BankAccount toEntity(BankAccountStateRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BankAccountStates state = null;
        String number = null;

        state = dto.state();
        number = dto.number();

        UUID id = null;
        UUID bankId = null;
        UUID userId = null;
        LocalDate registryDate = null;
        BigDecimal balance = null;

        BankAccount bankAccount = new BankAccount( id, bankId, userId, registryDate, number, balance, state );

        return bankAccount;
    }

    @Override
    public BankAccountCreationResponseDTO toBankAccountFullInformationResponseDTO(BankAccount bankAccount) {
        if ( bankAccount == null ) {
            return null;
        }

        UUID bankId = null;
        UUID userId = null;
        LocalDate registryDate = null;
        String number = null;
        BigDecimal balance = null;
        BankAccountStates state = null;

        bankId = bankAccount.getBankId();
        userId = bankAccount.getUserId();
        registryDate = bankAccount.getRegistryDate();
        number = bankAccount.getNumber();
        balance = bankAccount.getBalance();
        state = bankAccount.getState();

        BankAccountCreationResponseDTO bankAccountCreationResponseDTO = new BankAccountCreationResponseDTO( bankId, userId, registryDate, number, balance, state );

        return bankAccountCreationResponseDTO;
    }

    @Override
    public BankAccountActiveInformationResponseDTO toBankAccountActiveInformationResponseDTO(BankAccount bankAccount) {
        if ( bankAccount == null ) {
            return null;
        }

        UUID bankId = null;
        UUID userId = null;
        String number = null;
        BigDecimal balance = null;

        bankId = bankAccount.getBankId();
        userId = bankAccount.getUserId();
        number = bankAccount.getNumber();
        balance = bankAccount.getBalance();

        BankAccountActiveInformationResponseDTO bankAccountActiveInformationResponseDTO = new BankAccountActiveInformationResponseDTO( bankId, userId, number, balance );

        return bankAccountActiveInformationResponseDTO;
    }

    @Override
    public BankAccountDeactivateInformationResponseDTO toBankAccountDeactivateInformationResponseDTO(BankAccount bankAccount) {
        if ( bankAccount == null ) {
            return null;
        }

        UUID bankId = null;
        UUID userId = null;
        String number = null;
        BankAccountStates state = null;

        bankId = bankAccount.getBankId();
        userId = bankAccount.getUserId();
        number = bankAccount.getNumber();
        state = bankAccount.getState();

        BankAccountDeactivateInformationResponseDTO bankAccountDeactivateInformationResponseDTO = new BankAccountDeactivateInformationResponseDTO( bankId, userId, number, state );

        return bankAccountDeactivateInformationResponseDTO;
    }

    @Override
    public BankAccountSuccessTransactionResponseDTO toBankAccountSuccessTransactionDTO(BankAccount bankAccount) {
        if ( bankAccount == null ) {
            return null;
        }

        String senderBankAccountNumber = null;
        BigDecimal senderBankAccountBalance = null;

        BankAccountSuccessTransactionResponseDTO bankAccountSuccessTransactionResponseDTO = new BankAccountSuccessTransactionResponseDTO( senderBankAccountNumber, senderBankAccountBalance );

        return bankAccountSuccessTransactionResponseDTO;
    }

    @Override
    public BankAccountInformationResponseDTO toBankAccountInformationResponseDTO(BankAccountFullInformationDTO bankAccountFullInformationDTO) {
        if ( bankAccountFullInformationDTO == null ) {
            return null;
        }

        String bankName = null;
        String bankIdentityNumber = null;
        String userName = null;
        String userSurname = null;
        LocalDate birthdate = null;
        String phoneNumber = null;
        String passportNumber = null;
        LocalDate registryDate = null;
        String number = null;
        BigDecimal balance = null;
        BankAccountStates state = null;

        bankName = bankAccountFullInformationDTO.bankName();
        bankIdentityNumber = bankAccountFullInformationDTO.bankIdentityNumber();
        userName = bankAccountFullInformationDTO.userName();
        userSurname = bankAccountFullInformationDTO.userSurname();
        birthdate = bankAccountFullInformationDTO.birthdate();
        phoneNumber = bankAccountFullInformationDTO.phoneNumber();
        passportNumber = bankAccountFullInformationDTO.passportNumber();
        registryDate = bankAccountFullInformationDTO.registryDate();
        number = bankAccountFullInformationDTO.number();
        balance = bankAccountFullInformationDTO.balance();
        state = bankAccountFullInformationDTO.state();

        BankAccountInformationResponseDTO bankAccountInformationResponseDTO = new BankAccountInformationResponseDTO( bankName, bankIdentityNumber, userName, userSurname, birthdate, phoneNumber, passportNumber, registryDate, number, balance, state );

        return bankAccountInformationResponseDTO;
    }

    @Override
    public BankAccountCardIssueInformationDTO toBankAccountCardIssueInformationDTO(BankAccountFullInformationDTO bankAccountInformation) {
        if ( bankAccountInformation == null ) {
            return null;
        }

        UUID id = null;
        UUID bankId = null;
        String userName = null;
        String userSurname = null;
        BankAccountStates state = null;

        id = bankAccountInformation.id();
        bankId = bankAccountInformation.bankId();
        userName = bankAccountInformation.userName();
        userSurname = bankAccountInformation.userSurname();
        state = bankAccountInformation.state();

        BankAccountCardIssueInformationDTO bankAccountCardIssueInformationDTO = new BankAccountCardIssueInformationDTO( id, bankId, userName, userSurname, state );

        return bankAccountCardIssueInformationDTO;
    }
}
