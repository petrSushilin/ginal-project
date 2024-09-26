package ru.sberstart.finalproject.api.dto.bankaccount.request;

import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

/**
 * Шаблон для получения необходимых полей для изменения состояния банковского счета.
 */
public record BankAccountStateRequestDTO(String number, BankAccountStates state) {
}
