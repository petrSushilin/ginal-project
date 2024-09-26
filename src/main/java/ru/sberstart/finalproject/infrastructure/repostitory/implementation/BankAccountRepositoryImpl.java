package ru.sberstart.finalproject.infrastructure.repostitory.implementation;

import org.jooq.CaseConditionStep;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import ru.sberstart.finalproject.api.dto.bankaccount.inner.BankAccountFullInformationDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;

import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.BankAccountRepository;
import ru.sberstart.finalproject.jooq.tables.records.BankaccountsRecord;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.sberstart.finalproject.jooq.Tables.*;

/**
 * Реализация интерфейса BankAccountRepository для работы с банковскими счетами с помощью jOOQ.
 * Этот класс предоставляет методы для создания, получения, обновления и выполнения транзакций
 * с банковскими счетами в базе данных. Он использует DSLContext для выполнения SQL-запросов.
 */
public class BankAccountRepositoryImpl implements BankAccountRepository {
    private final DSLContext context;

    public BankAccountRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    private final List<TableField<BankaccountsRecord, ?>> BANK_ACCOUNT_FIELDS = List.of(
            BANKACCOUNTS.ID,
            BANKACCOUNTS.BANK_ID,
            BANKACCOUNTS.USER_ID,
            BANKACCOUNTS.REGISTRY_DATE,
            BANKACCOUNTS.NUMBER,
            BANKACCOUNTS.BALANCE,
            BANKACCOUNTS.STATE
    );

    private final List<TableField<?, ?>> FULL_INFORMATION_FIELDS = List.of(
            BANKACCOUNTS.ID,
            BANKS.ID,
            BANKS.NAME,
            BANKS.IDENTITY_NUMBER,
            USERS.ID,
            USERS.NAME,
            USERS.SURNAME,
            USERS.BIRTHDATE,
            USERS.PHONE_NUMBER,
            USERS.PASSPORT_NUMBER,
            BANKACCOUNTS.REGISTRY_DATE,
            BANKACCOUNTS.NUMBER,
            BANKACCOUNTS.BALANCE,
            BANKACCOUNTS.STATE
    );

    /**
     * Преобразует запись из базы данных в объект BankAccount.
     *
     * @param record Запись из таблицы BANKACCOUNTS.
     * @return Объект BankAccount, созданный на основе данных записи.
     */
    private static BankAccount buildBankAccount(Record record) {
        return new BankAccount.Builder()
                .withId(record.get(BANKACCOUNTS.ID))
                .withBankId(record.get(BANKACCOUNTS.BANK_ID))
                .withUserId(record.get(BANKACCOUNTS.USER_ID))
                .withRegistryDate(record.get(BANKACCOUNTS.REGISTRY_DATE))
                .withNumber(record.get(BANKACCOUNTS.NUMBER))
                .withBalance(record.get(BANKACCOUNTS.BALANCE))
                .withState(Enum.valueOf(BankAccountStates.class, record.get(BANKACCOUNTS.STATE)))
                .build();
    }

    /**
     * Преобразует запись из базы данных в объект BankAccountInformationResponseDTO.
     *
     * @param record Запись из таблицы BANKACCOUNTS JOIN BANKS JOIN USERS.
     * @return Объект BankAccountInformationResponseDTO, созданный на основе данных записи.
     */
    private static BankAccountFullInformationDTO buildBankAccountFullInformation(Record record) {
        return new BankAccountFullInformationDTO(
                record.get(BANKACCOUNTS.ID),
                record.get(BANKS.ID),
                record.get(BANKS.NAME),
                record.get(BANKS.IDENTITY_NUMBER),
                record.get(USERS.ID),
                record.get(USERS.NAME),
                record.get(USERS.SURNAME),
                record.get(USERS.BIRTHDATE),
                record.get(USERS.PHONE_NUMBER),
                record.get(USERS.PASSPORT_NUMBER),
                record.get(BANKACCOUNTS.REGISTRY_DATE),
                record.get(BANKACCOUNTS.NUMBER),
                record.get(BANKACCOUNTS.BALANCE),
                Enum.valueOf(BankAccountStates.class, record.get(BANKACCOUNTS.STATE)));
    }

    /**
     * Создает новый банковский счет в базе данных.
     * Этот метод вставляет новую запись банковского счета в таблицу BANKACCOUNTS.
     * Он принимает объект `bankAccount`, содержащий данные нового счета, и возвращает
     * созданный счет, обернутый в Optional.
     *
     * @param account Объект банковского счета, содержащий данные нового счета.
     * @return Optional, содержащий объект BankAccount, если создание прошло успешно,
     *         или пустой Optional, если запись не была создана.
     */
    @Override
    public Optional<BankAccount> create(BankAccount account) {
        context.insertInto(BANKACCOUNTS)
                .set(context.newRecord(BANKACCOUNTS, account))
                .execute();
        Record record = context
                .select(BANK_ACCOUNT_FIELDS)
                .from(BANKACCOUNTS)
                .where(BANKACCOUNTS.NUMBER.eq(account.getNumber()))
                .fetchOne();
        return Optional.ofNullable(record).map(BankAccountRepositoryImpl::buildBankAccount);
    }

    /**
     * Возвращает запись в таблице BankAccounts банковского счета по его номеру.
     * Этот метод выполняет запрос к базе данных для получения записи банковского счета
     * с указанным номером. Если запись найдена, она преобразуется в объект BankAccount
     * и возвращается в виде Optional.
     *
     * @param number Номер банковского счета.
     * @return Optional, содержащий объект BankAccount, если запись найдена,
     *         или пустой Optional, если запись не найдена.
     */
    @Override
    public Optional<BankAccount> getServiceInfoByNumber(String number) {
        Record record = context
                .select(BANK_ACCOUNT_FIELDS)
                .from(BANKACCOUNTS)
                .where(BANKACCOUNTS.NUMBER.eq(number))
                .fetchOne();
        return Optional.ofNullable(record).map(BankAccountRepositoryImpl::buildBankAccount);
    }

    /**
     * Возвращает все сведения по банковскому счет, включая полные сведения по владельцу и банку эмитету по номеру счета.
     * Этот метод выполняет запрос к базе данных для получения записи банковского счета
     * с указанным номером, объединяя запись со сведениями из таблиц Users и Banks. Если запись найдена,
     * она преобразуется в объект BankAccountInformationResponseDTO и возвращается в виде Optional.
     *
     * @param number Номер банковского счета.
     * @return Optional, содержащий объект BankAccount, если запись найдена,
     * или пустой Optional, если запись не найдена.
     */
    @Override
    public Optional<BankAccountFullInformationDTO> getByNumber(String number) {
        Record record = context
                .select(FULL_INFORMATION_FIELDS)
                .from(BANKACCOUNTS)
                .join(BANKS)
                .on(BANKACCOUNTS.BANK_ID.eq(BANKS.ID))
                .join(USERS)
                .on(BANKACCOUNTS.USER_ID.eq(USERS.ID))
                .where(BANKACCOUNTS.NUMBER.eq(number))
                .fetchOne();
        return Optional.ofNullable(record).map(BankAccountRepositoryImpl::buildBankAccountFullInformation);
    }

    /**
     * Обновляет состояние указанного банковского счета в базе данных.
     * Этот метод обновляет запись предоставленного банковского счета в таблице BANKACCOUNTS.
     * Он устанавливает новое состояние счета на основе предоставленного объекта `account` и возвращает
     * обновленный счет, обернутый в Optional.
     *
     * @param account Объект банковского счета, содержащий обновленное состояние.
     * @return Optional, содержащий обновленный объект BankAccount, если обновление прошло успешно,
     *         или пустой Optional, если запись не была обновлена.
     */
    @Override
    public Optional<BankAccount> updateState(BankAccount account) {
        context.update(BANKACCOUNTS)
                .set(context.newRecord(BANKACCOUNTS, account))
                .where(BANKACCOUNTS.NUMBER.eq(account.getNumber()))
                .execute();
        Record record = context
                .select(BANK_ACCOUNT_FIELDS)
                .from(BANKACCOUNTS)
                .where(BANKACCOUNTS.NUMBER.eq(account.getNumber()))
                .fetchOne();
        return Optional.ofNullable(record).map(BankAccountRepositoryImpl::buildBankAccount);
    }

    /**
     * Метод получения записей банковских счетов из БД, участвующих в процессе транзакции.
     *
     * @param parsedAccountNumbers Список номеров банковских счетов, участвующих в транзакции.
     * @return Optional, содержащий список объектов BankAccount, если записи найдены,
     *         или пустой Optional, если записи не найдены.
     */
    @Override
    public Optional<List<BankAccount>> getTransactionAccounts(List<String> parsedAccountNumbers) {
        List<Record> records = context
                .select(BANK_ACCOUNT_FIELDS)
                .from(BANKACCOUNTS)
                .where(BANKACCOUNTS.NUMBER.in(parsedAccountNumbers))
                .fetch();
        List<BankAccount> bankAccounts = records.stream()
                .map(BankAccountRepositoryImpl::buildBankAccount)
                .collect(Collectors.toList());
        return  Optional.of(bankAccounts).filter(list -> !list.isEmpty());
    }

    /**
     * Метод исполнения денежной транзакции.
     *
     * @param transactionAccountRecords Список банковских счетов, участвующих в транзакции.
     * @return Optional, содержащий список объектов BankAccount, если транзакция прошла успешно,
     *         или пустой Optional, если транзакция не удалась.
     * @throws IOException Если произошла ошибка ввода-вывода.
     */
    public Optional<List<BankAccount>> provideTransaction(List<BankAccount> transactionAccountRecords) throws IOException {
        BankAccount senderBankAccount;
        BankAccount receiverBankAccount;

        try {
            senderBankAccount = transactionAccountRecords.get(0);
            receiverBankAccount = transactionAccountRecords.get(1);
        } catch (IndexOutOfBoundsException e) {}

        CaseConditionStep<BigDecimal> balanceCase = transactionAccountRecords.stream().map(r -> DSL.case_()
                        .when(BANKACCOUNTS.NUMBER.eq(r.getNumber()), r.getBalance()))
                .reduce((case1, case2) -> (CaseConditionStep<BigDecimal>) case1.otherwise(case2)).orElseThrow();

        context.update(BANKACCOUNTS)
                .set(BANKACCOUNTS.BALANCE, balanceCase)
                .where(BANKACCOUNTS.NUMBER.in(transactionAccountRecords.stream().map(BankAccount::getNumber).toList()))
                .execute();

        List<BankAccount> bankAccounts = context
                .select(BANK_ACCOUNT_FIELDS)
                .from(BANKACCOUNTS)
                .where(BANKACCOUNTS.NUMBER.in(transactionAccountRecords.stream().map(BankAccount::getNumber).toList()))
                .stream()
                .map(BankAccountRepositoryImpl::buildBankAccount)
                .collect(Collectors.toList());
        return  Optional.of(bankAccounts).filter(list -> !list.isEmpty());
    }
}
