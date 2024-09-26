/*
 * This file is generated by jOOQ.
 */
package ru.sberstart.finalproject.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Cardsecrets implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cardNumber;
    private String holderName;
    private String holderSurname;
    private String validatePeriod;
    private String cvv;
    private String secretCode;

    public Cardsecrets() {}

    public Cardsecrets(Cardsecrets value) {
        this.cardNumber = value.cardNumber;
        this.holderName = value.holderName;
        this.holderSurname = value.holderSurname;
        this.validatePeriod = value.validatePeriod;
        this.cvv = value.cvv;
        this.secretCode = value.secretCode;
    }

    public Cardsecrets(
        String cardNumber,
        String holderName,
        String holderSurname,
        String validatePeriod,
        String cvv,
        String secretCode
    ) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.holderSurname = holderSurname;
        this.validatePeriod = validatePeriod;
        this.cvv = cvv;
        this.secretCode = secretCode;
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.CARD_NUMBER</code>.
     */
    public String getCardNumber() {
        return this.cardNumber;
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.CARD_NUMBER</code>.
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.HOLDER_NAME</code>.
     */
    public String getHolderName() {
        return this.holderName;
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.HOLDER_NAME</code>.
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.HOLDER_SURNAME</code>.
     */
    public String getHolderSurname() {
        return this.holderSurname;
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.HOLDER_SURNAME</code>.
     */
    public void setHolderSurname(String holderSurname) {
        this.holderSurname = holderSurname;
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.VALIDATE_PERIOD</code>.
     */
    public String getValidatePeriod() {
        return this.validatePeriod;
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.VALIDATE_PERIOD</code>.
     */
    public void setValidatePeriod(String validatePeriod) {
        this.validatePeriod = validatePeriod;
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.CVV</code>.
     */
    public String getCvv() {
        return this.cvv;
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.CVV</code>.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * Getter for <code>PUBLIC.CARDSECRETS.SECRET_CODE</code>.
     */
    public String getSecretCode() {
        return this.secretCode;
    }

    /**
     * Setter for <code>PUBLIC.CARDSECRETS.SECRET_CODE</code>.
     */
    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Cardsecrets other = (Cardsecrets) obj;
        if (this.cardNumber == null) {
            if (other.cardNumber != null)
                return false;
        }
        else if (!this.cardNumber.equals(other.cardNumber))
            return false;
        if (this.holderName == null) {
            if (other.holderName != null)
                return false;
        }
        else if (!this.holderName.equals(other.holderName))
            return false;
        if (this.holderSurname == null) {
            if (other.holderSurname != null)
                return false;
        }
        else if (!this.holderSurname.equals(other.holderSurname))
            return false;
        if (this.validatePeriod == null) {
            if (other.validatePeriod != null)
                return false;
        }
        else if (!this.validatePeriod.equals(other.validatePeriod))
            return false;
        if (this.cvv == null) {
            if (other.cvv != null)
                return false;
        }
        else if (!this.cvv.equals(other.cvv))
            return false;
        if (this.secretCode == null) {
            if (other.secretCode != null)
                return false;
        }
        else if (!this.secretCode.equals(other.secretCode))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.cardNumber == null) ? 0 : this.cardNumber.hashCode());
        result = prime * result + ((this.holderName == null) ? 0 : this.holderName.hashCode());
        result = prime * result + ((this.holderSurname == null) ? 0 : this.holderSurname.hashCode());
        result = prime * result + ((this.validatePeriod == null) ? 0 : this.validatePeriod.hashCode());
        result = prime * result + ((this.cvv == null) ? 0 : this.cvv.hashCode());
        result = prime * result + ((this.secretCode == null) ? 0 : this.secretCode.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cardsecrets (");

        sb.append(cardNumber);
        sb.append(", ").append(holderName);
        sb.append(", ").append(holderSurname);
        sb.append(", ").append(validatePeriod);
        sb.append(", ").append(cvv);
        sb.append(", ").append(secretCode);

        sb.append(")");
        return sb.toString();
    }
}
