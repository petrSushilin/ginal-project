package ru.sberstart.finalproject.bankaccount.api.dto;

import java.util.UUID;

/**
 * Шаблон для получения необходимых полей для создания сущности банковского счета.
 */
public record BankAccountCreationDTO (UUID bankId, UUID userId) {
}
