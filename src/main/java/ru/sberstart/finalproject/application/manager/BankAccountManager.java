package ru.sberstart.finalproject.application.manager;

import org.springframework.lang.NonNull;
import ru.sberstart.finalproject.domain.state.bankaccount.BankAccountState;
import ru.sberstart.finalproject.domain.state.bankaccount.BankAccountStateFactory;
import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountCreationRequestDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Класс для управления банковскими счетами (BankAccount) в рамках выполнения бизнес-логики.
 * Основные функции включают в себя: работу по заполнению сведений создаваемого счета,
 * проверку и изменение состояния банковских счетов.
 */
public class BankAccountManager {
    private static final Pattern PATTERN = Pattern.compile("^\\d{8}-[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");


    /**
     * Создает новый банковский счет на основе предоставленных данных.
     *
     * @param bankAccountCreationDTO DTO с данными для создания банковского счета.
     * @return Экземпляр созданного банковского счета.
     */
    public BankAccount creatingCustomizeAccount(@NonNull BankAccountCreationRequestDTO bankAccountCreationDTO) {
        return new BankAccount.Builder()
                .withBankId(bankAccountCreationDTO.bankId())
                .withUserId(bankAccountCreationDTO.userId())
                .withRegistryDate(LocalDate.now())
                .withNumber(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-"
                                                                            + bankAccountCreationDTO.userId())
                .withBalance(BigDecimal.ZERO)
                .withState(BankAccountStates.CREATED)
                .build();
    }

    /**
     * Проверяет, соответствует ли номер банковского счета заданному шаблону.
     *
     * @param bankNumber Номер банковского счета для проверки.
     * @return true, если номер соответствует шаблону, иначе false.
     */
    public boolean isInvalidateNumberPattern(String bankNumber) {
        return !PATTERN.asMatchPredicate().test(bankNumber);
    }

    /**
     * Проверяет, готовность предоставленных банковских счетов к выполнению транзакций.
     *
     * @param accounts Список банковских счетов для проверки.
     * @return true, если оба счета готовы к выполнению транзакций, иначе false.
     */
    public boolean reportAllAvailableTransactionExecution(@NonNull List<BankAccount> accounts) {
        return !accounts.stream()
                .map(BankAccountStateFactory::getState)
                .allMatch(BankAccountState::isReadyForTransaction);
    }

    public boolean reportUnavailableTransactionExecution(@NonNull List<BankAccount> accounts) {
        return accounts.stream()
                .map(BankAccountStateFactory::getState)
                .peek(System.out::println)
                .anyMatch(BankAccountState::isReadyForTransaction);
    }

    /**
     * Переводит банковский счет в состояние "Активный".
     *
     * @param account Экземпляр банковского счета, который необходимо активировать.
     */
    public void approveAccount(BankAccount account) {
        BankAccountStateFactory.getState(account).approveAccount(account);
    }

    /**
     * Переводит банковский счет в состояние "Приостановленный".
     *
     * @param account Экземпляр банковского счета, который необходимо приостановить.
     */
    public void suspendAccount(BankAccount account) {
        BankAccountStateFactory.getState(account).suspendAccount(account);
    }

    /**
     * Переводит банковский счет в состояние "Закрытый".
     *
     * @param account Экземпляр банковского счета, который необходимо закрыть.
     */
    public void closeAccount(BankAccount account) {
        BankAccountStateFactory.getState(account).closeAccount(account);
    }
}