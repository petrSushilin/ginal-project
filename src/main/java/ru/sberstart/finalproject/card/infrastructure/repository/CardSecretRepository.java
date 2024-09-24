package ru.sberstart.finalproject.card.infrastructure.repository;

import ru.sberstart.finalproject.card.api.dto.inner.CardSecretRecord;
import ru.sberstart.finalproject.card.domain.entity.CardSecret;

import java.util.Optional;

public interface CardSecretRepository {
    Optional<CardSecret> create(CardSecretRecord cardSecret);

    Optional<CardSecret> getByNumber(String number);
}
