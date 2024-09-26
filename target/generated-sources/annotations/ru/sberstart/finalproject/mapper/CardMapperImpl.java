package ru.sberstart.finalproject.mapper;

import javax.annotation.processing.Generated;
import ru.sberstart.finalproject.api.dto.card.inner.CardSecretInformationDTO;
import ru.sberstart.finalproject.api.dto.card.response.CardStateResponseDTO;
import ru.sberstart.finalproject.domain.enitity.card.Card;
import ru.sberstart.finalproject.domain.enitity.card.CardSecret;
import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T14:51:11+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
public class CardMapperImpl implements CardMapper {

    @Override
    public CardStateResponseDTO toCardStateResponseDTO(Card createdCard) {
        if ( createdCard == null ) {
            return null;
        }

        String number = null;
        CardStates state = null;

        number = createdCard.getNumber();
        state = createdCard.getState();

        CardStateResponseDTO cardStateResponseDTO = new CardStateResponseDTO( number, state );

        return cardStateResponseDTO;
    }

    @Override
    public CardSecretInformationDTO toCardSecretInformation(CardSecret cardSecret) {
        if ( cardSecret == null ) {
            return null;
        }

        String cardNumber = null;
        String holderName = null;
        String holderSurname = null;
        String validatePeriod = null;
        String cVV = null;

        cardNumber = cardSecret.cardNumber();
        holderName = cardSecret.holderName();
        holderSurname = cardSecret.holderSurname();
        validatePeriod = cardSecret.validatePeriod();
        cVV = cardSecret.CVV();

        CardSecretInformationDTO cardSecretInformationDTO = new CardSecretInformationDTO( cardNumber, holderName, holderSurname, validatePeriod, cVV );

        return cardSecretInformationDTO;
    }
}
