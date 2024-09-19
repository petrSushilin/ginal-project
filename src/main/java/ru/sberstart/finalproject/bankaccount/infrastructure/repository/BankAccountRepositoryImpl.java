package ru.sberstart.finalproject.bankaccount.infrastructure.repository;

import org.jooq.CaseConditionStep;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;

import static ru.sberstart.finalproject.jooq.Tables.BANKACCOUNTS;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;
import ru.sberstart.finalproject.jooq.tables.records.BankaccountsRecord;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BankAccountRepositoryImpl implements BankAccountRepository {
    private DSLContext context;

    public BankAccountRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    private final List<TableField<BankaccountsRecord, ?>> BANK_ACCOUNT_FIELDS = List.of(
            BANKACCOUNTS.ID,
            BANKACCOUNTS.BANK_ID,
            BANKACCOUNTS.USER_ID,
            BANKACCOUNTS.REGISTRY_DATE,
            BANKACCOUNTS.NUMBER,
            BANKACCOUNTS.STATE
    );

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
     * Метод создания сущности лицевого счета в БД.
     *
     * @param bankAccount
     * @return
     */
    @Override
    public BankAccount create(BankAccount bankAccount) {
        return context
                .insertInto(BANKACCOUNTS)
                .set(context.newRecord(BANKACCOUNTS, bankAccount))
                .returning()
                .fetchOne(BankAccountRepositoryImpl::buildBankAccount);
    }

    /**
     * Метод получения сущности лицевого счета в БД по уникальному номеру.
     *
     * @param number
     * @return BankAccount
     */
    @Override
    public BankAccount getByNumber(String number) {
        return context
                .select(BANK_ACCOUNT_FIELDS)
                .from(BANKACCOUNTS)
                .where(BANKACCOUNTS.NUMBER.eq(number))
                .fetchOne(BankAccountRepositoryImpl::buildBankAccount);
    }

    /**
     * Метод обновлнеия состояния лицевого счета в БД.
     *
     * @param account
     */
    @Override
    public BankAccount updateState(BankAccount account) {
        return context
                .update(BANKACCOUNTS)
                .set(context.newRecord(BANKACCOUNTS, account))
                .where(BANKACCOUNTS.NUMBER.eq(account.getNumber()))
                .returning()
                .fetchOne(BankAccountRepositoryImpl::buildBankAccount);
    }

    /**
     * Метод получения записей банковских счетов из БД, участвующих в процессе транзакции.
     *
     * @param parsedAccountNumbers
     * @return
     */
    @Override
    public List<BankAccount> getTransactionAccounts(List<String> parsedAccountNumbers) {
        return context
                .select(BANK_ACCOUNT_FIELDS)
                .from(BANKACCOUNTS)
                .where(BANKACCOUNTS.NUMBER.in(parsedAccountNumbers))
                .fetch(BankAccountRepositoryImpl::buildBankAccount);
    }

    /**
     * Метод исполнения денежной транзакции.
     *
     * @param transactionAccountRecords
     * @return List of BankAccount
     */
    public List<BankAccount> provideTransaction(List<BankAccount> transactionAccountRecords) throws IOException {
        BankAccount senderBankAccount = transactionAccountRecords.get(0);
        BankAccount receiverBankAccount = transactionAccountRecords.get(1);

        CaseConditionStep<BigDecimal> balanceCase = DSL.case_()
                .when(BANKACCOUNTS.NUMBER.eq(senderBankAccount.getNumber()), senderBankAccount.getBalance())
                .when(BANKACCOUNTS.NUMBER.eq(receiverBankAccount.getNumber()), receiverBankAccount.getBalance());

        return context
                .update(BANKACCOUNTS)
                .set(BANKACCOUNTS.BALANCE, balanceCase)
                .where(BANKACCOUNTS.NUMBER.in(senderBankAccount.getNumber(), receiverBankAccount.getNumber()))
                .returning()
                .fetch(BankAccountRepositoryImpl::buildBankAccount);
    }
}
