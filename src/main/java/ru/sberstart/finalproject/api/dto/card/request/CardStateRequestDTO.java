package ru.sberstart.finalproject.api.dto.card.request;

import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;

public record CardStateRequestDTO (String number, CardStates state) {
}
