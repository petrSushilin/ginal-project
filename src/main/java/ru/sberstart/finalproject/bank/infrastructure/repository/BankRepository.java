package ru.sberstart.finalproject.bank.infrastructure.repository;

import ru.sberstart.finalproject.bank.domain.entity.Bank;

import java.util.Optional;

public interface BankRepository {
    Optional<Bank> create(Bank bankAccount);

    Optional<Bank> getByIdentityNumber(String number);

    Optional<Bank> update(Bank bankAccount);
}
