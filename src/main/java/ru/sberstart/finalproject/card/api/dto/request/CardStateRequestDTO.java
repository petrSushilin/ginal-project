package ru.sberstart.finalproject.card.api.dto.request;

import ru.sberstart.finalproject.card.domain.entity.enums.CardStates;

public record CardStateRequestDTO (String number, CardStates state) {
}
