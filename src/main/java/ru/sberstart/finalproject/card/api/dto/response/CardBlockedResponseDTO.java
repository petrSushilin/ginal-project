package ru.sberstart.finalproject.card.api.dto.response;

import ru.sberstart.finalproject.card.domain.entity.enums.CardStates;

public record CardBlockedResponseDTO (String number, CardStates state) {
}
