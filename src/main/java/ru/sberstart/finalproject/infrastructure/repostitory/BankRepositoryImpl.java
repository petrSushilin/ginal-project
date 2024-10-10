package ru.sberstart.finalproject.infrastructure.repostitory;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import ru.sberstart.finalproject.domain.enitity.bank.Bank;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.BankRepository;
import ru.sberstart.finalproject.jooq.tables.records.BanksRecord;

import java.util.List;
import java.util.Optional;

import static ru.sberstart.finalproject.jooq.Tables.BANKS;

/**
 * Реализация интерфейса BankRepository с использованием jOOQ для операций с базой данных.
 * Основные функциональные особенности: создание нового банка в базе данных; получение банка по идентификационному номеру;
 * обновление существующего банка в базе данных; методы возвращают Optional, что означает, что результат может быть пустым,
 * если операция не удалась.
 *
 */
public class BankRepositoryImpl implements BankRepository {
    private final DSLContext context;

    /**
     * Конструктор для создания нового экземпляра BankRepositoryImpl с заданным DSLContext.
     *
     * @param context DSLContext для выполнения операций с базой данных
     */
    public BankRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    private final List<TableField<BanksRecord, ?>> BANK_FIELDS = List.of(
            BANKS.ID,
            BANKS.NAME,
            BANKS.IDENTITY_NUMBER
    );

    /**
     * Создает сущность Bank из записи базы данных.
     *
     * @param record запись базы данных
     * @return созданная сущность Bank
     */
    private static Bank buildBankAccount(Record record) {
        return new Bank.Builder()
                .withId(record.get(BANKS.ID))
                .withName(record.get(BANKS.NAME))
                .withIdentityNumber(record.get(BANKS.IDENTITY_NUMBER))
                .build();
    }

    /**
     * Создает новую сущность Bank в базе данных.
     *
     * @param bank сущность Bank для создания
     * @return Optional, содержащий созданную сущность Bank, или пустой, если создание не удалось
     */
    @Override
    public Optional<Bank> create(Bank bank) {
        context.insertInto(BANKS)
                .set(BANKS.NAME, bank.getName())
                .set(BANKS.IDENTITY_NUMBER, bank.getIdentityNumber())
                .execute();
        Record record = context.select(BANK_FIELDS)
                .from(BANKS)
                .where(BANKS.NAME.eq(bank.getName()))
                .fetchOne();
        return Optional.ofNullable(record).map(BankRepositoryImpl::buildBankAccount);
    }

    /**
     * Получает сущность Bank по идентификационному номеру.
     *
     * @param number идентификационный номер сущности Bank для получения
     * @return Optional, содержащий найденную сущность Bank, или пустой, если не найдено
     */
    @Override
    public Optional<Bank> getByIdentityNumber(String number) {
        Record record = context
                .select(BANK_FIELDS)
                .from(BANKS)
                .where(BANKS.IDENTITY_NUMBER.eq(number))
                .fetchOne();
        return Optional.ofNullable(record).map(BankRepositoryImpl::buildBankAccount);
    }

    /**
     * Обновляет существующую сущность Bank в базе данных.
     *
     * @param bank сущность Bank для обновления
     * @return Optional, содержащий обновленную сущность Bank, или пустой, если обновление не удалось
     */
    @Override
    public Optional<Bank> update(Bank bank) {
        context.update(BANKS)
                .set(context.newRecord(BANKS, bank))
                .where(BANKS.IDENTITY_NUMBER.eq(bank.getIdentityNumber()))
                .execute();
        Record record = context.select(BANK_FIELDS)
                .from(BANKS)
                .where(BANKS.NAME.eq(bank.getName()))
                .fetchOne();
        return Optional.ofNullable(record).map(BankRepositoryImpl::buildBankAccount);
    }
}
