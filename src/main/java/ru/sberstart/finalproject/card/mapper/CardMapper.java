package ru.sberstart.finalproject.card.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.card.api.dto.response.CardStateResponseDTO;
import ru.sberstart.finalproject.card.domain.entity.Card;

@Mapper(componentModel = "cardService")
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardStateResponseDTO toCardStateResponseDTO(Card createdCard);
}