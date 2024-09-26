package ru.sberstart.finalproject.api.dto.card.inner;

public record CardSecretRecord (String cardNumber, String holderName, String holderSurname, String validatePeriod, String CVV, String secretCode) {
}
