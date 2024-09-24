package ru.sberstart.finalproject.card.infrastructure.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import ru.sberstart.finalproject.card.api.dto.inner.CardSecretRecord;
import ru.sberstart.finalproject.card.domain.entity.CardSecret;
import ru.sberstart.finalproject.jooq.tables.records.CardsecretsRecord;

import java.util.List;
import java.util.Optional;

import static ru.sberstart.finalproject.jooq.Tables.CARDS;
import static ru.sberstart.finalproject.jooq.Tables.CARDSECRETS;

public class CardSecretRepositoryImpl implements CardSecretRepository {
    private DSLContext context;

    public CardSecretRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    private final List<TableField<CardsecretsRecord, ?>> CARD_SECRET_FIELDS = List.of(
            CARDSECRETS.ID,
            CARDSECRETS.HOLDER_NAME,
            CARDSECRETS.HOLDER_SURNAME,
            CARDSECRETS.VALIDATE_PERIOD,
            CARDSECRETS.SECRET_CODE
    );

    private static CardSecret buildCardSecret(Record record) {
        return new CardSecret.Builder()
                .withId(record.get(CARDSECRETS.ID))
                .withHolderName(record.get(CARDSECRETS.HOLDER_NAME))
                .withHolderSurname(record.get(CARDSECRETS.HOLDER_SURNAME))
                .withValidatePeriod(record.get(CARDSECRETS.VALIDATE_PERIOD))
                .withSecretCode(record.get(CARDSECRETS.SECRET_CODE))
                .build();
    }

    @Override
    public Optional<CardSecret> create(CardSecretRecord cardSecret) {
        Record record = context
                .insertInto(CARDSECRETS)
                .set(context.newRecord(CARDSECRETS, cardSecret))
                .returning()
                .fetchOne();
        return Optional.ofNullable(record).map(CardSecretRepositoryImpl::buildCardSecret);
    }

    @Override
    public Optional<CardSecret> getByNumber(String number) {
        Record record = context
                .select(CARD_SECRET_FIELDS)
                .from(CARDSECRETS)
                .join(CARDS)
                .on(CARDS.CARD_SECRET_ID.eq(CARDSECRETS.ID))
                .where(CARDS.NUMBER.eq(number))
                .fetchOne();

        return Optional.ofNullable(record).map(CardSecretRepositoryImpl::buildCardSecret);
    }
}
