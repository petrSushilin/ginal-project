package ru.sberstart.finalproject.card.api.dto.inner;

import java.util.UUID;

public class CardCustomizingDTO {
    private UUID bankAccountId;
    private UUID bankId;
    private UUID cardSecretId;

    public UUID getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(UUID bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public UUID getBankId() {
        return bankId;
    }

    public void setBankId(UUID bankId) {
        this.bankId = bankId;
    }

    public UUID getCardSecretId() {
        return cardSecretId;
    }

    public void setCardSecretId(UUID cardSecretId) {
        this.cardSecretId = cardSecretId;
    }
}
