package ru.sberstart.finalproject.api.dto.bank;

/**
 * Представляет собой объект передачи данных для информации о банке.
 *
 * @param name Название банка.
 * @param identityNumber Идентификационный номер банка.
 */
public record BankInformationDTO(String name, String identityNumber) {
}