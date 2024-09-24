package ru.sberstart.finalproject.card.infrastructure.repository;

import ru.sberstart.finalproject.card.api.dto.inner.CardGeneralInformationDTO;
import ru.sberstart.finalproject.card.domain.entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Optional<Card> create(Card card);

    Optional<CardGeneralInformationDTO> getByNumber(String number);

    Optional<Card> getServiceInfoByNumber(String number);

    List<CardGeneralInformationDTO> getCardsByUser(String userPhoneNumber);

    Optional<Card> updateState(Card card);
}
