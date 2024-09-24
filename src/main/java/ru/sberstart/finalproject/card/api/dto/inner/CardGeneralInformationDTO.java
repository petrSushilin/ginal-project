package ru.sberstart.finalproject.card.api.dto.inner;

import ru.sberstart.finalproject.card.domain.entity.enums.CardStates;

import java.math.BigDecimal;
import java.util.UUID;

public record CardGeneralInformationDTO (UUID id, String bankName, String bankIdentityNumber, UUID bankAccountId, String userName, String userSurname, String bankAccountNumber, BigDecimal bankAccountBalance, String number, CardStates state) {
}
