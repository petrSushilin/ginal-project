package ru.sberstart.finalproject.domain.enitity.card;


import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;
import ru.sberstart.finalproject.domain.enitity.interfaces.State;
import ru.sberstart.finalproject.domain.enitity.interfaces.Stateful;

import java.util.Objects;
import java.util.UUID;

public class Card implements Stateful {
    private UUID id;
    private UUID bankaccountId;
    private String number;
    private CardStates state;


    private Card(UUID id, UUID bankaccountId, String number, CardStates state) {
        this.id = id;
        this.bankaccountId = bankaccountId;
        this.number = number;
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBankAccountId() {
        return bankaccountId;
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
        private UUID bankaccountId;
        private String number;
        private CardStates state;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withBankAccountId(UUID bankaccountId) {
            this.bankaccountId = bankaccountId;
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
            return new Card(id, bankaccountId, number, state);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(bankaccountId, card.bankaccountId) && Objects.equals(number, card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankaccountId, number);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", bankAccountId=" + bankaccountId +
                ", number='" + number + '\'' +
                ", state=" + state +
                '}';
    }
}
