package ru.sberstart.finalproject.bankaccount.application.service;

import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.bankaccount.mapper.BankAccountMapper;
import ru.sberstart.finalproject.bankaccount.infrastructure.repository.BankAccountRepositoryImpl;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountActiveInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountCreationDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountDeactivateInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountFullInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountStateDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountSuccessTransactionDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountTransactionDTO;
import ru.sberstart.finalproject.bankaccount.application.manager.BankAccountManager;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.global.exceptions.ForbiddenTransactionException;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;

/**
 * Класс управления бизнес-логикой при работе с банковскими счетами.
 */
public class BankAccountService {
    private final BankAccountRepositoryImpl repository;
    private final BankAccountManager manager;
    private final TransactionService transactionService;

    public BankAccountService(BankAccountRepositoryImpl repository, TransactionService transactionService) {
        this.repository = repository;
        this.transactionService = transactionService;
        this.manager = new BankAccountManager();
    }

    /**
     * Процесс создания банковского счета.
     * Сначала выполняется донастройка сущности, затем выполняется создание банковского счета.
     * После корректного добавления банковского счета в БД, происходит получение записи из БД
     * и возврат ожидаемого DTO сущности.
     *
     * @param bankAccountCreationDTO
     */
    public BankAccountFullInformationResponseDTO createBankAccount(BankAccountCreationDTO bankAccountCreationDTO) {
        BankAccount bankAccountRecord = repository.create(manager.customize(bankAccountCreationDTO));

        return BankAccountMapper.INSTANCE.toBankAccountFullInformationResponseDTO(bankAccountRecord);
    }

    /**
     * Процесс подтверждения банковского счета.
     * Сначала выполняется изменение состояния банковского счета, затем выполняется обновление записи в БД.
     * После корректного изменения состояния банковского счета в БД, происходит отправка ожидаемого DTO сущности.
     *
     * @param stateDTO
     * @return BankAccountActiveInformationResponseDTO
     */
    public BankAccountActiveInformationResponseDTO approveBankAccount(BankAccountStateDTO stateDTO) {
        BankAccount bankAccountRecord = consistencyAccountRecordWithState(stateDTO);

        return BankAccountMapper.INSTANCE.toBankAccountActiveInformationResponseDTO(bankAccountRecord);
    }

    /**
     * Процесс приостановки действия банковского счета.
     * Сначала выполняется изменение состояния банковского счета, затем выполняется обновление записи в БД.
     * После корректного изменения состояния банковского счета в БД, происходит отправка ожидаемого DTO сущности.
     *
     * @param stateDTO
     * @return BankAccountDeactivateInformationResponseDTO
     */
    public BankAccountDeactivateInformationResponseDTO suspendBankAccount(BankAccountStateDTO stateDTO) {
        BankAccount bankAccountRecord = consistencyAccountRecordWithState(stateDTO);

        return BankAccountMapper.INSTANCE.toBankAccountDeactivateInformationResponseDTO(repository.updateState(bankAccountRecord));
    }

    /**
     * Процесс закрытия банковского счета.
     * Сначала выполняется изменение состояния банковского счета, затем выполняется обновление записи в БД.
     * После корректного изменения состояния банковского счета в БД, происходит отправка ожидаемого DTO сущности.
     *
     * @param stateDTO
     * @return BankAccountDeactivateInformationResponseDTO
     */
    @Transactional(rollbackFor = UnsuccessfulOperationException.class)
    public BankAccountDeactivateInformationResponseDTO closeBankAccount(BankAccountStateDTO stateDTO) throws UnsuccessfulOperationException {
        BankAccount bankAccountRecord = consistencyAccountRecordWithState(stateDTO);

        return BankAccountMapper.INSTANCE.toBankAccountDeactivateInformationResponseDTO(repository.updateState(bankAccountRecord));
    }

    /**
     * Внутренний метод, реализующий дережирование изменением состояния лицевых счетов,
     * в качестве передаваемых параметров метод получает новое значение состония,
     * влияющее на применяемый паттерн поведения.
     *
     * @param stateDTO
     * @return BankAccount
     */
    private BankAccount consistencyAccountRecordWithState(BankAccountStateDTO stateDTO) {
        BankAccount account = repository.getByNumber(stateDTO.number());
        switch (stateDTO.state()) {
            case CREATED -> throw new UnsupportedOperationException();
            case ACTIVE -> manager.approveAccount(account);
            case STOPPED -> manager.suspendAccount(account);
            case CLOSED -> manager.closeAccount(account);
        }
        return account;
    }

    @Transactional(rollbackFor = ForbiddenTransactionException.class)
    public BankAccountSuccessTransactionDTO transaction(BankAccountTransactionDTO transactionDTO) {
        BankAccount senderBankAccount = transactionService.executeTransaction(transactionDTO);

        return BankAccountMapper.INSTANCE.toBankAccountSuccessTransactionDTO(senderBankAccount);
    }
}
