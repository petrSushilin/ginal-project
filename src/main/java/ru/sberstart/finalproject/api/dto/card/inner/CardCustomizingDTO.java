package ru.sberstart.finalproject.api.dto.card.inner;

import java.util.UUID;

public class CardCustomizingDTO {
    private UUID bankAccountId;
    private String cardNumber;

    public UUID getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(UUID bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
