package ru.sberstart.finalproject.bank.api.dto;

/**
 * Представляет собой объект передачи данных для информации о банке.
 *
 * @param name Название банка.
 * @param identityNumber Идентификационный номер банка.
 */
public record BankInformationDTO(String name, String identityNumber) {
}