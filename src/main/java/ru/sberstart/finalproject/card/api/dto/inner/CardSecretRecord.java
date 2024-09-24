package ru.sberstart.finalproject.card.api.dto.inner;

public record CardSecretRecord (String name, String surname, String validatePeriod, String CVV, String secretCode) {
}
