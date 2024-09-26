package ru.sberstart.finalproject.domain.enitity.bank;


import java.util.Objects;
import java.util.UUID;

public class Bank {
    private UUID id;
    private String name;
    private String identityNumber;

    public Bank(UUID id, String name, String identityNumber) {
        this.id = id;
        this.name = name;
        this.identityNumber = identityNumber;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String identityNumber;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
            return this;
        }

        public Bank build() {
            return new Bank(id, name, identityNumber);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name) && Objects.equals(identityNumber, bank.identityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, identityNumber);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                '}';
    }
}
