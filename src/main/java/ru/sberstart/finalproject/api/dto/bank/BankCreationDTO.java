package ru.sberstart.finalproject.api.dto.bank;

/**
 * Представляет собой объект передачи данных для создания банковской сущности.
 *
 * @param name Название банка.
 * @param identityNumber Идентификационный номер банка.
 */
public record BankCreationDTO(String name, String identityNumber) {
}