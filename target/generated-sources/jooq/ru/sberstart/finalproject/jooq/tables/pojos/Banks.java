/*
 * This file is generated by jOOQ.
 */
package ru.sberstart.finalproject.jooq.tables.pojos;


import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Banks implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String identityNumber;

    public Banks() {}

    public Banks(Banks value) {
        this.id = value.id;
        this.name = value.name;
        this.identityNumber = value.identityNumber;
    }

    public Banks(
        UUID id,
        String name,
        String identityNumber
    ) {
        this.id = id;
        this.name = name;
        this.identityNumber = identityNumber;
    }

    /**
     * Getter for <code>PUBLIC.BANKS.ID</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>PUBLIC.BANKS.ID</code>.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Getter for <code>PUBLIC.BANKS.NAME</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>PUBLIC.BANKS.NAME</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>PUBLIC.BANKS.IDENTITY_NUMBER</code>.
     */
    public String getIdentityNumber() {
        return this.identityNumber;
    }

    /**
     * Setter for <code>PUBLIC.BANKS.IDENTITY_NUMBER</code>.
     */
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Banks other = (Banks) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.identityNumber == null) {
            if (other.identityNumber != null)
                return false;
        }
        else if (!this.identityNumber.equals(other.identityNumber))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.identityNumber == null) ? 0 : this.identityNumber.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Banks (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(identityNumber);

        sb.append(")");
        return sb.toString();
    }
}
