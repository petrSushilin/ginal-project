package ru.sberstart.finalproject.card.api.dto.response;

import java.time.LocalDate;

public record CardSecretResponseDTO (String number, LocalDate validatePeriod, String CVV) {
}