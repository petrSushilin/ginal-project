package ru.sberstart.finalproject.mapper;

import java.util.UUID;
import javax.annotation.processing.Generated;
import ru.sberstart.finalproject.api.dto.bank.BankCreationDTO;
import ru.sberstart.finalproject.api.dto.bank.BankFullInformationDTO;
import ru.sberstart.finalproject.api.dto.bank.BankInformationDTO;
import ru.sberstart.finalproject.domain.enitity.bank.Bank;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T14:51:11+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
public class BankMapperImpl implements BankMapper {

    @Override
    public Bank toEntity(BankCreationDTO creationDTO) {
        if ( creationDTO == null ) {
            return null;
        }

        String name = null;
        String identityNumber = null;

        name = creationDTO.name();
        identityNumber = creationDTO.identityNumber();

        UUID id = null;

        Bank bank = new Bank( id, name, identityNumber );

        return bank;
    }

    @Override
    public Bank toEntity(BankInformationDTO bankInformationDTO) {
        if ( bankInformationDTO == null ) {
            return null;
        }

        String name = null;
        String identityNumber = null;

        name = bankInformationDTO.name();
        identityNumber = bankInformationDTO.identityNumber();

        UUID id = null;

        Bank bank = new Bank( id, name, identityNumber );

        return bank;
    }

    @Override
    public BankInformationDTO toBankInformationDTO(Bank bank) {
        if ( bank == null ) {
            return null;
        }

        String name = null;
        String identityNumber = null;

        name = bank.getName();
        identityNumber = bank.getIdentityNumber();

        BankInformationDTO bankInformationDTO = new BankInformationDTO( name, identityNumber );

        return bankInformationDTO;
    }

    @Override
    public BankFullInformationDTO toBankFullInformationDTO(Bank bank) {
        if ( bank == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String identityNumber = null;

        id = bank.getId();
        name = bank.getName();
        identityNumber = bank.getIdentityNumber();

        BankFullInformationDTO bankFullInformationDTO = new BankFullInformationDTO( id, name, identityNumber );

        return bankFullInformationDTO;
    }
}
