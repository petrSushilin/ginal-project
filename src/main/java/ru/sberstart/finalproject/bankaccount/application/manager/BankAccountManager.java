package ru.sberstart.finalproject.bankaccount.application.manager;

import org.springframework.lang.NonNull;
import ru.sberstart.finalproject.bankaccount.domain.states.BankAccountState;
import ru.sberstart.finalproject.bankaccount.domain.states.BankAccountStateFactory;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountCreationDTO;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Класс управления записями учетных записей (BankAccount) в рамках выполнения бизнес-логики.
 */
public class BankAccountManager {
    /**
     * Метод, применяющийся для донастройки шаблона создания банковского счета.
     *
     * @param bankAccountCreationDTO
     * @return BankAccount
     */
    public BankAccount customize(@NonNull BankAccountCreationDTO bankAccountCreationDTO) {
        return BankAccount.builder()
                .withBankId(bankAccountCreationDTO.bankId())
                .withUserId(bankAccountCreationDTO.userId())
                .withRegistryDate(LocalDate.now())
                .withNumber(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-"
                                                                            + bankAccountCreationDTO.userId())
                .withState(BankAccountStates.CREATED)
                .build();
    }

    public boolean reportNotAvailableTransactionExecution(@NonNull List<BankAccount> accounts) {
        return !accounts.stream()
                .map(BankAccountStateFactory::getState)
                .allMatch(BankAccountState::isReadyForTransaction);
    }

    /**
     * Метод, применяющийся для перевода экземпляра банковского счета в состояние активного.
     *
     * @param account
     */
    public void approveAccount(BankAccount account) {
        BankAccountStateFactory.getState(account).approveAccount(account);
    }

    /**
     * Метод, применяющийся для перевода экземпляра банковского счета в состояние приостановленного.
     *
     * @param account
     */
    public void suspendAccount(BankAccount account) {
        BankAccountStateFactory.getState(account).suspendAccount(account);
    }

    /**
     * Метод, применяющийся для перевода экземпляра банковского счета в состояние закрытого.
     *
     * @param account
     */
    public void closeAccount(BankAccount account) {
        BankAccountStateFactory.getState(account).closeAccount(account);
    }
}
