package ru.sberstart.finalproject.infrastructure.repostitory.implementation;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.TableField;
import ru.sberstart.finalproject.api.dto.card.inner.CardGeneralInformationDTO;
import ru.sberstart.finalproject.domain.enitity.card.Card;
import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.CardRepository;
import ru.sberstart.finalproject.jooq.tables.records.CardsRecord;

import java.util.List;
import java.util.Optional;

import static ru.sberstart.finalproject.jooq.Tables.BANKACCOUNTS;
import static ru.sberstart.finalproject.jooq.Tables.BANKS;
import static ru.sberstart.finalproject.jooq.Tables.CARDS;
import static ru.sberstart.finalproject.jooq.Tables.USERS;

public class CardRepositoryImpl implements CardRepository {
    DSLContext context;

    public CardRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    private final List<TableField<CardsRecord, ?>> CARD_FIELDS = List.of(
            CARDS.ID,
            CARDS.BANKACCOUNT_ID,
            CARDS.NUMBER,
            CARDS.STATE
    );

    private final List<Field<?>> CARD_GENERAL_INFO_FIELDS = List.of(
            CARDS.ID,
            BANKS.NAME.as("bankName"),
            BANKS.IDENTITY_NUMBER,
            CARDS.BANKACCOUNT_ID,
            USERS.NAME.as("userName"),
            USERS.SURNAME.as("userSurname"),
            BANKACCOUNTS.NUMBER.as("bankAccountNumber"),
            BANKACCOUNTS.BALANCE,
            CARDS.NUMBER.as("cardNumber"),
            CARDS.STATE
    );

    private static Card buildCard(Record record) {
        return new Card.Builder()
                .withId(record.get(CARDS.ID))
                .withBankAccountId(record.get(CARDS.BANKACCOUNT_ID))
                .withNumber(record.get(CARDS.NUMBER))
                .withState(CardStates.valueOf(record.get(CARDS.STATE)))
                .build();
    }

    public static CardGeneralInformationDTO buildGeneralInfoCard(Record record) {
        return new CardGeneralInformationDTO(
                record.get(CARDS.ID),
                record.get("bankName", String.class),
                record.get(BANKS.IDENTITY_NUMBER),
                record.get(CARDS.BANKACCOUNT_ID),
                record.get("userName", String.class),
                record.get("userSurname", String.class),
                record.get("bankAccountNumber", String.class),
                record.get(BANKACCOUNTS.BALANCE),
                record.get("cardNumber", String.class),
                CardStates.valueOf(record.get(CARDS.STATE))
        );
    }

    @Override
    public Optional<Card> create(Card card) {
        context.insertInto(CARDS)
                .set(context.newRecord(CARDS, card))
                .execute();
        Record record = context
                .select(CARD_FIELDS)
                .from(CARDS)
                .where(CARDS.NUMBER.eq(card.getNumber()))
                .fetchOne();
        return Optional.ofNullable(record).map(CardRepositoryImpl::buildCard);
    }

    @Override
    public Optional<CardGeneralInformationDTO> getByNumber(String number) {
        System.out.println(number);
        Record record = context
                .select(CARD_GENERAL_INFO_FIELDS)
                .from(CARDS)
                .join(BANKACCOUNTS)
                .on(CARDS.BANKACCOUNT_ID.eq(BANKACCOUNTS.ID))
                .join(BANKS)
                .on(BANKACCOUNTS.BANK_ID.eq(BANKS.ID))
                .join(USERS)
                .on(BANKACCOUNTS.USER_ID.eq(USERS.ID))
                .where(CARDS.NUMBER.eq(number))
                .fetchOne();
        return Optional.ofNullable(record).map(CardRepositoryImpl::buildGeneralInfoCard);
    }

    @Override
    public Optional<Card> getServiceInfoByNumber(String number) {
        Record record = context
                .select(CARD_FIELDS)
                .from(CARDS)
                .where(CARDS.NUMBER.eq(number))
                .fetchOne();
        return Optional.ofNullable(record).map(CardRepositoryImpl::buildCard);
    }

    public List<CardGeneralInformationDTO> getCardsByUser(String userPhoneNumber) {
        return  context
                .select(CARD_GENERAL_INFO_FIELDS)
                .from(CARDS)
                .join(BANKACCOUNTS)
                .on(CARDS.BANKACCOUNT_ID.eq(BANKACCOUNTS.ID))
                .join(BANKS)
                .on(BANKACCOUNTS.BANK_ID.eq(BANKS.ID))
                .join(USERS)
                .on(BANKACCOUNTS.USER_ID.eq(USERS.ID))
                .where(USERS.PHONE_NUMBER.eq(userPhoneNumber))
                .fetchInto(CardGeneralInformationDTO.class);
    }

    @Override
    public Optional<Card> updateState(Card card) {
        context.update(CARDS)
                .set(context.newRecord(CARDS, card))
                .where(CARDS.NUMBER.eq(card.getNumber()))
                .execute();
        Record record = context
                .select(CARD_FIELDS)
                .from(CARDS)
                .where(CARDS.NUMBER.eq(card.getNumber()))
                .fetchOne();
        return Optional.ofNullable(record).map(CardRepositoryImpl::buildCard);
    }
}
