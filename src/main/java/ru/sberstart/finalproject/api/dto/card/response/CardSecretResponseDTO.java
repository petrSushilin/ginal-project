package ru.sberstart.finalproject.api.dto.card.response;

import java.time.LocalDate;

public record CardSecretResponseDTO (String cardNumber, LocalDate validatePeriod, String CVV) {
}