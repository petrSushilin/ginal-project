package ru.sberstart.finalproject.infrastructure.repostitory;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import ru.sberstart.finalproject.domain.enitity.card.CardSecret;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.CardSecretRepository;
import ru.sberstart.finalproject.jooq.tables.records.CardsecretsRecord;

import java.util.List;
import java.util.Optional;

import static ru.sberstart.finalproject.jooq.Tables.CARDSECRETS;

public class CardSecretRepositoryImpl implements CardSecretRepository {
    private DSLContext context;

    public CardSecretRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    private final List<TableField<CardsecretsRecord, ?>> CARD_SECRET_FIELDS = List.of(
            CARDSECRETS.CARD_NUMBER,
            CARDSECRETS.HOLDER_NAME,
            CARDSECRETS.HOLDER_SURNAME,
            CARDSECRETS.VALIDATE_PERIOD,
            CARDSECRETS.CVV,
            CARDSECRETS.SECRET_CODE
    );

    private static CardSecret buildCardSecret(Record record) {
        return new CardSecret.Builder()
                .withCardNumber(record.get(CARDSECRETS.CARD_NUMBER))
                .withHolderName(record.get(CARDSECRETS.HOLDER_NAME))
                .withHolderSurname(record.get(CARDSECRETS.HOLDER_SURNAME))
                .withValidatePeriod(record.get(CARDSECRETS.VALIDATE_PERIOD))
                .withCVV(record.get(CARDSECRETS.CVV))
                .withSecretCode(record.get(CARDSECRETS.SECRET_CODE))
                .build();
    }

    @Override
    public Optional<CardSecret> create(CardSecret cardSecret) {
        context.insertInto(CARDSECRETS)
                .set(context.newRecord(CARDSECRETS, cardSecret))
                .execute();

        Record record = context
                .select(CARD_SECRET_FIELDS)
                .from(CARDSECRETS)
                .where(CARDSECRETS.CARD_NUMBER.eq(cardSecret.cardNumber()))
                .fetchOne();
        return Optional.ofNullable(record).map(CardSecretRepositoryImpl::buildCardSecret);
    }

    @Override
    public Optional<CardSecret> getByNumber(String cardNumber) {
        Record record = context
                .select(CARD_SECRET_FIELDS)
                .from(CARDSECRETS)
                .where(CARDSECRETS.CARD_NUMBER.eq(cardNumber))
                .fetchOne();

        return Optional.ofNullable(record).map(CardSecretRepositoryImpl::buildCardSecret);
    }
}
