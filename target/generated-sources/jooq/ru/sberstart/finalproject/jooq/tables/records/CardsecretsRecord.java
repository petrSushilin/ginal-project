/*
 * This file is generated by jOOQ.
 */
package ru.sberstart.finalproject.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import ru.sberstart.finalproject.jooq.tables.Cardsecrets;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CardsecretsRecord extends UpdatableRecordImpl<CardsecretsRecord> implements Record6<String, String, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.CARD_NUMBER</code>.
     */
    public void setCardNumber(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.CARD_NUMBER</code>.
     */
    public String getCardNumber() {
        return (String) get(0);
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.HOLDER_NAME</code>.
     */
    public void setHolderName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.HOLDER_NAME</code>.
     */
    public String getHolderName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.HOLDER_SURNAME</code>.
     */
    public void setHolderSurname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.HOLDER_SURNAME</code>.
     */
    public String getHolderSurname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.VALIDATE_PERIOD</code>.
     */
    public void setValidatePeriod(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.VALIDATE_PERIOD</code>.
     */
    public String getValidatePeriod() {
        return (String) get(3);
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.CVV</code>.
     */
    public void setCvv(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.CVV</code>.
     */
    public String getCvv() {
        return (String) get(4);
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.SECRET_CODE</code>.
     */
    public void setSecretCode(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.SECRET_CODE</code>.
     */
    public String getSecretCode() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<String, String, String, String, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Cardsecrets.CARDSECRETS.CARD_NUMBER;
    }

    @Override
    public Field<String> field2() {
        return Cardsecrets.CARDSECRETS.HOLDER_NAME;
    }

    @Override
    public Field<String> field3() {
        return Cardsecrets.CARDSECRETS.HOLDER_SURNAME;
    }

    @Override
    public Field<String> field4() {
        return Cardsecrets.CARDSECRETS.VALIDATE_PERIOD;
    }

    @Override
    public Field<String> field5() {
        return Cardsecrets.CARDSECRETS.CVV;
    }

    @Override
    public Field<String> field6() {
        return Cardsecrets.CARDSECRETS.SECRET_CODE;
    }

    @Override
    public String component1() {
        return getCardNumber();
    }

    @Override
    public String component2() {
        return getHolderName();
    }

    @Override
    public String component3() {
        return getHolderSurname();
    }

    @Override
    public String component4() {
        return getValidatePeriod();
    }

    @Override
    public String component5() {
        return getCvv();
    }

    @Override
    public String component6() {
        return getSecretCode();
    }

    @Override
    public String value1() {
        return getCardNumber();
    }

    @Override
    public String value2() {
        return getHolderName();
    }

    @Override
    public String value3() {
        return getHolderSurname();
    }

    @Override
    public String value4() {
        return getValidatePeriod();
    }

    @Override
    public String value5() {
        return getCvv();
    }

    @Override
    public String value6() {
        return getSecretCode();
    }

    @Override
    public CardsecretsRecord value1(String value) {
        setCardNumber(value);
        return this;
    }

    @Override
    public CardsecretsRecord value2(String value) {
        setHolderName(value);
        return this;
    }

    @Override
    public CardsecretsRecord value3(String value) {
        setHolderSurname(value);
        return this;
    }

    @Override
    public CardsecretsRecord value4(String value) {
        setValidatePeriod(value);
        return this;
    }

    @Override
    public CardsecretsRecord value5(String value) {
        setCvv(value);
        return this;
    }

    @Override
    public CardsecretsRecord value6(String value) {
        setSecretCode(value);
        return this;
    }

    @Override
    public CardsecretsRecord values(String value1, String value2, String value3, String value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CardsecretsRecord
     */
    public CardsecretsRecord() {
        super(Cardsecrets.CARDSECRETS);
    }

    /**
     * Create a detached, initialised CardsecretsRecord
     */
    public CardsecretsRecord(String cardNumber, String holderName, String holderSurname, String validatePeriod, String cvv, String secretCode) {
        super(Cardsecrets.CARDSECRETS);

        setCardNumber(cardNumber);
        setHolderName(holderName);
        setHolderSurname(holderSurname);
        setValidatePeriod(validatePeriod);
        setCvv(cvv);
        setSecretCode(secretCode);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised CardsecretsRecord
     */
    public CardsecretsRecord(ru.sberstart.finalproject.jooq.tables.pojos.Cardsecrets value) {
        super(Cardsecrets.CARDSECRETS);

        if (value != null) {
            setCardNumber(value.getCardNumber());
            setHolderName(value.getHolderName());
            setHolderSurname(value.getHolderSurname());
            setValidatePeriod(value.getValidatePeriod());
            setCvv(value.getCvv());
            setSecretCode(value.getSecretCode());
            resetChangedOnNotNull();
        }
    }
}
