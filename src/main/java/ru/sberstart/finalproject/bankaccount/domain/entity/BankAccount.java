package ru.sberstart.finalproject.bankaccount.domain.entity;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Сущность записи банковского счета.
 */
public class BankAccount {
    private UUID id;
    private UUID bankId;
    private UUID userId;
    private LocalDate registryDate;
    private String number;
    private BigDecimal balance;
    private BankAccountStates state;

    private BankAccount(UUID id, UUID bankId, UUID userId, LocalDate registryDate, String number, BigDecimal balance, BankAccountStates state) {
        this.id = id;
        this.bankId = bankId;
        this.userId = userId;
        this.registryDate = registryDate;
        this.number = number;
        this.balance = balance;
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBankId() {
        return bankId;
    }

    public UUID getUserId() {
        return userId;
    }

    public LocalDate getRegistryDate() {
        return registryDate;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BankAccountStates getState() {
        return state;
    }

    public void setState(BankAccountStates state) {
        this.state = state;
    }

    public static BankAccount.Builder builder() {
        return new BankAccount.Builder();
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static class Builder {
        private UUID id;
        private UUID bankId;
        private UUID userId;
        private LocalDate registryDate;
        private String number;
        private BigDecimal balance;
        private BankAccountStates state;

        public BankAccount build() {
            return new BankAccount(this.id, this.bankId, this.userId, this.registryDate, this.number, this.balance, this.state);
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withBankId(UUID bankId) {
            this.bankId = bankId;
            return this;
        }

        public Builder withUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder withRegistryDate(LocalDate registryDate) {
            this.registryDate = registryDate;
            return this;
        }

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder withState(BankAccountStates state) {
            this.state = state;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(bankId, that.bankId) && Objects.equals(userId, that.userId) && Objects.equals(registryDate, that.registryDate) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankId, userId, registryDate, number);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", userId=" + userId +
                ", registryDate=" + registryDate +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", state=" + state +
                '}';
    }
}
