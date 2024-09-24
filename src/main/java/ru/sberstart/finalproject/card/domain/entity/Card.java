package ru.sberstart.finalproject.card.domain.entity;


import ru.sberstart.finalproject.card.domain.entity.enums.CardStates;

import java.util.Objects;
import java.util.UUID;

public class Card {
    private UUID id;
    private UUID bankAccountId;
    private UUID cardSecretId;
    private String number;
    private CardStates state;


    private Card(UUID id, UUID bankAccountId, UUID cardSecretId, String number, CardStates state) {
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.cardSecretId = cardSecretId;
        this.number = number;
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBankAccountId() {
        return bankAccountId;
    }

    public UUID getCardSecretId() {
        return cardSecretId;
    }

    public String getNumber() {
        return number;
    }

    public CardStates getState() {
        return state;
    }

    public void setState(CardStates state) {
        this.state = state;
    }

    public static class Builder {
        private UUID id;
        private UUID bankAccountId;
        private UUID cardSecretId;
        private String number;
        private CardStates state;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withBankAccountId(UUID bankAccountId) {
            this.bankAccountId = bankAccountId;
            return this;
        }

        public Builder withCardSecretId(UUID cardSecretId) {
            this.cardSecretId = cardSecretId;
            return this;
        }

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withState(CardStates state) {
            this.state = state;
            return this;
        }

        public Card build() {
            return new Card(id, bankAccountId, cardSecretId, number, state);
        }
    }
}
