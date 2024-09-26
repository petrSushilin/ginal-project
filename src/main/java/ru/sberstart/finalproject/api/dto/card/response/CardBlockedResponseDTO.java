package ru.sberstart.finalproject.api.dto.card.response;

import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;

public record CardBlockedResponseDTO (String number, CardStates state) {
}
