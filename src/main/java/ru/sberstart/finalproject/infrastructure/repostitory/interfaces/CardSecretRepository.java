package ru.sberstart.finalproject.infrastructure.repostitory.interfaces;

import ru.sberstart.finalproject.api.dto.card.inner.CardSecretRecord;
import ru.sberstart.finalproject.domain.enitity.card.CardSecret;

import java.util.Optional;

public interface CardSecretRepository {
    Optional<CardSecret> create(CardSecret cardSecret);

    Optional<CardSecret> getByNumber(String number);
}
