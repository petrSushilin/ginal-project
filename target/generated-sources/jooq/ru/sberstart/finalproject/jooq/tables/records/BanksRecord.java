/*
 * This file is generated by jOOQ.
 */
package ru.sberstart.finalproject.jooq.tables.records;


import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import ru.sberstart.finalproject.jooq.tables.Banks;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BanksRecord extends UpdatableRecordImpl<BanksRecord> implements Record3<UUID, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.BANKS.ID</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKS.ID</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>PUBLIC.BANKS.NAME</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKS.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.BANKS.IDENTITY_NUMBER</code>.
     */
    public void setIdentityNumber(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.BANKS.IDENTITY_NUMBER</code>.
     */
    public String getIdentityNumber() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Banks.BANKS.ID;
    }

    @Override
    public Field<String> field2() {
        return Banks.BANKS.NAME;
    }

    @Override
    public Field<String> field3() {
        return Banks.BANKS.IDENTITY_NUMBER;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getIdentityNumber();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getIdentityNumber();
    }

    @Override
    public BanksRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public BanksRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public BanksRecord value3(String value) {
        setIdentityNumber(value);
        return this;
    }

    @Override
    public BanksRecord values(UUID value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BanksRecord
     */
    public BanksRecord() {
        super(Banks.BANKS);
    }

    /**
     * Create a detached, initialised BanksRecord
     */
    public BanksRecord(UUID id, String name, String identityNumber) {
        super(Banks.BANKS);

        setId(id);
        setName(name);
        setIdentityNumber(identityNumber);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised BanksRecord
     */
    public BanksRecord(ru.sberstart.finalproject.jooq.tables.pojos.Banks value) {
        super(Banks.BANKS);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setIdentityNumber(value.getIdentityNumber());
            resetChangedOnNotNull();
        }
    }
}
