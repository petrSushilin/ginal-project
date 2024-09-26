package ru.sberstart.finalproject.infrastructure.repostitory.interfaces;

import ru.sberstart.finalproject.domain.enitity.bank.Bank;

import java.util.Optional;

public interface BankRepository {
    Optional<Bank> create(Bank bankAccount);

    Optional<Bank> getByIdentityNumber(String number);

    Optional<Bank> update(Bank bankAccount);
}
