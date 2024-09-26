package ru.sberstart.finalproject.infrastructure.repostitory.interfaces;

import ru.sberstart.finalproject.api.dto.card.inner.CardGeneralInformationDTO;
import ru.sberstart.finalproject.domain.enitity.card.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Optional<Card> create(Card card);

    Optional<CardGeneralInformationDTO> getByNumber(String number);

    Optional<Card> getServiceInfoByNumber(String number);

    List<CardGeneralInformationDTO> getCardsByUser(String userPhoneNumber);

    Optional<Card> updateState(Card card);
}
