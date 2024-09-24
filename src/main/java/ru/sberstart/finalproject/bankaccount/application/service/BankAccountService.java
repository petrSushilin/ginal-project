package ru.sberstart.finalproject.bankaccount.application.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.bankaccount.api.dto.inner.BankAccountCardIssueInformationDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.inner.BankAccountFullInformationDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountActiveInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountCreationRequestDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountDeactivateInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountCreationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountStateRequestDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountSuccessTransactionResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountTransactionRequestDTO;
import ru.sberstart.finalproject.bankaccount.mapper.BankAccountMapper;
import ru.sberstart.finalproject.bankaccount.infrastructure.repository.BankAccountRepositoryImpl;
import ru.sberstart.finalproject.bankaccount.application.manager.BankAccountManager;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.global.exceptions.ForbiddenTransactionException;
import ru.sberstart.finalproject.global.exceptions.RecordNotFoundException;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;

import java.util.Optional;

/**
 * Класс управления бизнес-логикой при работе с банковскими счетами.
 * Основные функциональные особенности: Создание, подтверждение, приостановка и закрытие банковских счетов.
 * Валидация номеров банковских счетов. Выполнение транзакций между банковскими счетами.
 * Ограничения: Номера банковских счетов должны соответствовать определенному шаблону.
 * Некоторые состояния банковских счетов не могут быть изменены напрямую.
 */
public class BankAccountService {
    private final BankAccountRepositoryImpl repository;
    private final BankAccountManager manager;
    private final TransactionService transactionService;

    public BankAccountService(BankAccountRepositoryImpl repository, TransactionService transactionService, BankAccountManager manager) {
        this.repository = repository;
        this.transactionService = transactionService;
        this.manager = manager;
    }

    /**
     * Процесс создания банковского счета.
     * Сначала выполняется донастройка сущности, затем выполняется создание банковского счета.
     * После корректного добавления банковского счета в БД, происходит получение записи из БД
     * и возврат ожидаемого DTO сущности.
     *
     * @param bankAccountCreationDTO DTO с информацией для создания банковского счета.
     * @return BankAccountFullInformationResponseDTO с полной информацией о созданном банковском счете.
     */
    public BankAccountCreationResponseDTO createBankAccount(BankAccountCreationRequestDTO bankAccountCreationDTO) {
        BankAccount bankAccount = manager.creatingCustomizeAccount(bankAccountCreationDTO);

        BankAccount bankAccountRecord = repository.create(bankAccount).orElseThrow(UnsuccessfulOperationException::new);

        return BankAccountMapper.INSTANCE.toBankAccountFullInformationResponseDTO(bankAccountRecord);
    }

    /**
     * Получение информации о банковском счете по его номеру.
     *
     * @param bankAccountNumber Номер банковского счета.
     * @return BankAccountInformationResponseDTO с информацией о банковском счете.
     */
    public BankAccountInformationResponseDTO getByNumber(String bankAccountNumber) {
        BankAccountFullInformationDTO bankAccountInformation = getBankAccountFullInformationByNumber(bankAccountNumber);
        return BankAccountMapper.INSTANCE.toBankAccountInformationResponseDTO(bankAccountInformation);
    }

    /**
     * Получение информации о банковском счете по его номеру для выпуска карты.
     *
     * @param bankAccountNumber Номер банковского счета.
     * @return BankAccountCardIssueInformationDTO с информацией о банковском счете.
     */
    public BankAccountCardIssueInformationDTO getBankAccountCardIssueInformationByNumber(String bankAccountNumber) {
        BankAccountFullInformationDTO bankAccountInformation = getBankAccountFullInformationByNumber(bankAccountNumber);
        return BankAccountMapper.INSTANCE.toBankAccountCardIssueInformationDTO(bankAccountInformation);
    }

    /**
     * Внутренний метод для получения полной информации о банковском счете по его номеру.
     * Перед обращением к БД проводится валидация номера счета.
     *
     * @param bankAccountNumber Номер банковского счета.
     * @return BankAccountFullInformationDTO с информацией о банковском счете.
     */
    private BankAccountFullInformationDTO getBankAccountFullInformationByNumber(String bankAccountNumber) {
        if (manager.isInvalidateNumberPattern(bankAccountNumber)) throw new IllegalArgumentException();

        return repository.getByNumber(bankAccountNumber).orElseThrow(RecordNotFoundException::new);
    }

    /**
     * Процесс подтверждения банковского счета.
     * Сначала выполняется изменение состояния банковского счета, затем выполняется обновление записи в БД.
     * После корректного изменения состояния банковского счета в БД, происходит отправка ожидаемого DTO сущности.
     *
     * @param stateDTO DTO с информацией о состоянии банковского счета.
     * @return BankAccountActiveInformationResponseDTO с информацией об активированном банковском счете.
     */
    public BankAccountActiveInformationResponseDTO approveBankAccount(BankAccountStateRequestDTO stateDTO) {
        BankAccount bankAccount = consistencyAccountRecordWithState(stateDTO);

        BankAccount bankAccountRecord = repository.updateState(bankAccount).orElseThrow(UnsuccessfulOperationException::new);

        return BankAccountMapper.INSTANCE.toBankAccountActiveInformationResponseDTO(bankAccountRecord);
    }

    /**
     * Процесс приостановки действия банковского счета.
     * Сначала выполняется изменение состояния банковского счета, затем выполняется обновление записи в БД.
     * После корректного изменения состояния банковского счета в БД, происходит отправка ожидаемого DTO сущности.
     *
     * @param stateDTO DTO с информацией о состоянии банковского счета.
     * @return BankAccountDeactivateInformationResponseDTO с информацией о приостановленном банковском счете.
     */
    public BankAccountDeactivateInformationResponseDTO suspendBankAccount(BankAccountStateRequestDTO stateDTO) {
        BankAccount bankAccount = consistencyAccountRecordWithState(stateDTO);

        BankAccount bankAccountRecord = repository.updateState(bankAccount).orElseThrow(UnsuccessfulOperationException::new);

        return BankAccountMapper.INSTANCE.toBankAccountDeactivateInformationResponseDTO(bankAccountRecord);
    }

    /**
     * Процесс закрытия банковского счета.
     * Сначала выполняется изменение состояния банковского счета, затем выполняется обновление записи в БД.
     * После корректного изменения состояния банковского счета в БД, происходит отправка ожидаемого DTO сущности.
     *
     * @param stateDTO DTO с информацией о состоянии банковского счета.
     * @return BankAccountDeactivateInformationResponseDTO с информацией о закрытом банковском счете.
     */
    public BankAccountDeactivateInformationResponseDTO closeBankAccount(BankAccountStateRequestDTO stateDTO) {
        BankAccount bankAccount = consistencyAccountRecordWithState(stateDTO);

        BankAccount bankAccountRecord = repository.updateState(bankAccount).orElseThrow(UnsuccessfulOperationException::new);

        return BankAccountMapper.INSTANCE.toBankAccountDeactivateInformationResponseDTO(bankAccountRecord);
    }

    /**
     * Внутренний метод, реализующий изменение состояния банковского счета.
     * В зависимости от переданного состояния, выполняется соответствующее действие.
     *
     * @param stateDTO DTO с информацией о состоянии банковского счета.
     * @return BankAccount с обновленным состоянием.
     */
    private BankAccount consistencyAccountRecordWithState(BankAccountStateRequestDTO stateDTO) {
        BankAccount account = repository.getServiceInfoByNumber(stateDTO.number()).orElseThrow(UnsuccessfulOperationException::new);
        switch (stateDTO.state()) {
            case CREATED -> throw new UnsupportedOperationException();
            case ACTIVE -> manager.approveAccount(account);
            case STOPPED -> manager.suspendAccount(account);
            case CLOSED -> manager.closeAccount(account);
        }
        return account;
    }

    /**
     * Выполнение транзакции между банковскими счетами.
     * Транзакция выполняется с использованием цепочки обработчиков.
     * В случае ошибки транзакция откатывается.
     *
     * @param transactionDTO DTO с информацией о транзакции.
     * @return BankAccountSuccessTransactionDTO с информацией об успешной транзакции.
     */
    @Transactional(rollbackFor = ForbiddenTransactionException.class)
    public BankAccountSuccessTransactionResponseDTO executeTransaction(BankAccountTransactionRequestDTO transactionDTO) {
        BankAccount senderBankAccount = transactionService.executeTransaction(transactionDTO);

        return BankAccountMapper.INSTANCE.toBankAccountSuccessTransactionDTO(senderBankAccount);
    }
}
