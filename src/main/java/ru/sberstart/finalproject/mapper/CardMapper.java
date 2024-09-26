package ru.sberstart.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.api.dto.card.inner.CardSecretInformationDTO;
import ru.sberstart.finalproject.api.dto.card.inner.CardSecretRecord;
import ru.sberstart.finalproject.api.dto.card.response.CardStateResponseDTO;
import ru.sberstart.finalproject.domain.enitity.card.Card;
import ru.sberstart.finalproject.domain.enitity.card.CardSecret;

@Mapper(componentModel = "default", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardStateResponseDTO toCardStateResponseDTO(Card createdCard);

    CardSecretInformationDTO toCardSecretInformation(CardSecret cardSecret);
}