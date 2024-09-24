package ru.sberstart.finalproject.bank.application.service;

import ru.sberstart.finalproject.bank.BankMapper;
import ru.sberstart.finalproject.bank.api.dto.BankCreationDTO;
import ru.sberstart.finalproject.bank.api.dto.BankFullInformationDTO;
import ru.sberstart.finalproject.bank.api.dto.BankInformationDTO;
import ru.sberstart.finalproject.bank.domain.entity.Bank;
import ru.sberstart.finalproject.bank.infrastructure.repository.BankRepositoryImpl;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;


public class BankService {
    private final BankRepositoryImpl repository;

    public BankService(BankRepositoryImpl repository) {
        this.repository = repository;
    }

    public BankFullInformationDTO create(BankCreationDTO bankCreationDTO) {
        Bank bankCreationRecord = BankMapper.INSTANCE.toEntity(bankCreationDTO);

        Bank bank = repository.create(bankCreationRecord).orElseThrow(UnsuccessfulOperationException::new);

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
