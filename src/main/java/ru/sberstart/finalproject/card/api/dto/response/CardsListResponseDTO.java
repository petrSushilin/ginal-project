package ru.sberstart.finalproject.card.api.dto.response;

import ru.sberstart.finalproject.card.domain.entity.enums.CardStates;

import java.util.Map;

public record CardsListResponseDTO (Map<String, CardStates> cardStatesMap) {
}