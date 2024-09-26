package ru.sberstart.finalproject.api.dto.card.inner;

public record CardSecretInformationDTO(String cardNumber, String holderName, String holderSurname, String validatePeriod, String CVV) {
}
