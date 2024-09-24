package ru.sberstart.finalproject.bankaccount.api.dto.request;

import java.util.UUID;

/**
 * Шаблон для получения необходимых полей для создания сущности банковского счета.
 */
public record BankAccountCreationRequestDTO(UUID bankId, UUID userId) {
}
