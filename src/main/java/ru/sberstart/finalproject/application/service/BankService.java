package ru.sberstart.finalproject.application.service;

import ru.sberstart.finalproject.mapper.BankMapper;
import ru.sberstart.finalproject.api.dto.bank.BankCreationDTO;
import ru.sberstart.finalproject.api.dto.bank.BankFullInformationDTO;
import ru.sberstart.finalproject.api.dto.bank.BankInformationDTO;
import ru.sberstart.finalproject.domain.enitity.bank.Bank;
import ru.sberstart.finalproject.infrastructure.repostitory.BankRepositoryImpl;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;


public class BankService {
    private final BankRepositoryImpl repository;

    public BankService(BankRepositoryImpl repository) {
        this.repository = repository;
    }

    public BankFullInformationDTO create(BankCreationDTO bankCreationDTO) {
        Bank bankCreationRecord = BankMapper.INSTANCE.toEntity(bankCreationDTO);

        Bank bank = repository.create(bankCreationRecord).orElseThrow();

        return BankMapper.INSTANCE.toBankFullInformationDTO(bank);
    }

    public BankInformationDTO getByIdentityNumber(String identityNumber) {
        Bank bank = repository.getByIdentityNumber(identityNumber).orElseThrow(UnsuccessfulOperationException::new);

        return BankMapper.INSTANCE.toBankInformationDTO(bank);
    }

    public BankInformationDTO update(BankInformationDTO bankInformationDTO) {
        Bank bankUpdateRecord = BankMapper.INSTANCE.toEntity(bankInformationDTO);

        Bank bank = repository.update(bankUpdateRecord).orElseThrow(UnsuccessfulOperationException::new);

        return BankMapper.INSTANCE.toBankInformationDTO(bank);
    }
}
