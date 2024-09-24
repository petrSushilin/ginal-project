package ru.sberstart.finalproject.bankaccount.api.dto.request;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

/**
 * Шаблон для получения необходимых полей для изменения состояния банковского счета.
 */
public record BankAccountStateRequestDTO(String number, BankAccountStates state) {
}
