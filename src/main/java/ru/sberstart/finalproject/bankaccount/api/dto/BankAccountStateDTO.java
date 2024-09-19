package ru.sberstart.finalproject.bankaccount.api.dto;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

/**
 * Шаблон для получения необходимых полей для изменения состояния банковского счета.
 */
public record BankAccountStateDTO (String number, BankAccountStates state) {
}
