/*
 * This file is generated by jOOQ.
 */
package ru.sberstart.finalproject.jooq.tables.records;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import ru.sberstart.finalproject.jooq.tables.Bankaccounts;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BankaccountsRecord extends UpdatableRecordImpl<BankaccountsRecord> implements Record7<UUID, UUID, UUID, LocalDate, String, BigDecimal, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.BANKACCOUNTS.ID</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKACCOUNTS.ID</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>PUBLIC.BANKACCOUNTS.BANK_ID</code>.
     */
    public void setBankId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKACCOUNTS.BANK_ID</code>.
     */
    public UUID getBankId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>PUBLIC.BANKACCOUNTS.USER_ID</code>.
     */
    public void setUserId(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKACCOUNTS.USER_ID</code>.
     */
    public UUID getUserId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>PUBLIC.BANKACCOUNTS.REGISTRY_DATE</code>.
     */
    public void setRegistryDate(LocalDate value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKACCOUNTS.REGISTRY_DATE</code>.
     */
    public LocalDate getRegistryDate() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>PUBLIC.BANKACCOUNTS.NUMBER</code>.
     */
    public void setNumber(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKACCOUNTS.NUMBER</code>.
     */
    public String getNumber() {
        return (String) get(4);
    }

    /**
     * Setter for <code>PUBLIC.BANKACCOUNTS.BALANCE</code>.
     */
    public void setBalance(BigDecimal value) {
        set(5, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKACCOUNTS.BALANCE</code>.
     */
    public BigDecimal getBalance() {
        return (BigDecimal) get(5);
    }

    /**
     * Setter for <code>PUBLIC.BANKACCOUNTS.STATE</code>.
     */
    public void setState(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKACCOUNTS.STATE</code>.
     */
    public String getState() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<UUID, UUID, UUID, LocalDate, String, BigDecimal, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<UUID, UUID, UUID, LocalDate, String, BigDecimal, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Bankaccounts.BANKACCOUNTS.ID;
    }

    @Override
    public Field<UUID> field2() {
        return Bankaccounts.BANKACCOUNTS.BANK_ID;
    }

    @Override
    public Field<UUID> field3() {
        return Bankaccounts.BANKACCOUNTS.USER_ID;
    }

    @Override
    public Field<LocalDate> field4() {
        return Bankaccounts.BANKACCOUNTS.REGISTRY_DATE;
    }

    @Override
    public Field<String> field5() {
        return Bankaccounts.BANKACCOUNTS.NUMBER;
    }

    @Override
    public Field<BigDecimal> field6() {
        return Bankaccounts.BANKACCOUNTS.BALANCE;
    }

    @Override
    public Field<String> field7() {
        return Bankaccounts.BANKACCOUNTS.STATE;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getBankId();
    }

    @Override
    public UUID component3() {
        return getUserId();
    }

    @Override
    public LocalDate component4() {
        return getRegistryDate();
    }

    @Override
    public String component5() {
        return getNumber();
    }

    @Override
    public BigDecimal component6() {
        return getBalance();
    }

    @Override
    public String component7() {
        return getState();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getBankId();
    }

    @Override
    public UUID value3() {
        return getUserId();
    }

    @Override
    public LocalDate value4() {
        return getRegistryDate();
    }

    @Override
    public String value5() {
        return getNumber();
    }

    @Override
    public BigDecimal value6() {
        return getBalance();
    }

    @Override
    public String value7() {
        return getState();
    }

    @Override
    public BankaccountsRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public BankaccountsRecord value2(UUID value) {
        setBankId(value);
        return this;
    }

    @Override
    public BankaccountsRecord value3(UUID value) {
        setUserId(value);
        return this;
    }

    @Override
    public BankaccountsRecord value4(LocalDate value) {
        setRegistryDate(value);
        return this;
    }

    @Override
    public BankaccountsRecord value5(String value) {
        setNumber(value);
        return this;
    }

    @Override
    public BankaccountsRecord value6(BigDecimal value) {
        setBalance(value);
        return this;
    }

    @Override
    public BankaccountsRecord value7(String value) {
        setState(value);
        return this;
    }

    @Override
    public BankaccountsRecord values(UUID value1, UUID value2, UUID value3, LocalDate value4, String value5, BigDecimal value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BankaccountsRecord
     */
    public BankaccountsRecord() {
        super(Bankaccounts.BANKACCOUNTS);
    }

    /**
     * Create a detached, initialised BankaccountsRecord
     */
    public BankaccountsRecord(UUID id, UUID bankId, UUID userId, LocalDate registryDate, String number, BigDecimal balance, String state) {
        super(Bankaccounts.BANKACCOUNTS);

        setId(id);
        setBankId(bankId);
        setUserId(userId);
        setRegistryDate(registryDate);
        setNumber(number);
        setBalance(balance);
        setState(state);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised BankaccountsRecord
     */
    public BankaccountsRecord(ru.sberstart.finalproject.jooq.tables.pojos.Bankaccounts value) {
        super(Bankaccounts.BANKACCOUNTS);

        if (value != null) {
            setId(value.getId());
            setBankId(value.getBankId());
            setUserId(value.getUserId());
            setRegistryDate(value.getRegistryDate());
            setNumber(value.getNumber());
            setBalance(value.getBalance());
            setState(value.getState());
            resetChangedOnNotNull();
        }
    }
}
