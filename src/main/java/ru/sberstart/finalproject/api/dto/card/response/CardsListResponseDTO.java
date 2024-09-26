package ru.sberstart.finalproject.api.dto.card.response;

import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;

import java.util.Map;

public record CardsListResponseDTO (Map<String, CardStates> cardStatesMap) {
}