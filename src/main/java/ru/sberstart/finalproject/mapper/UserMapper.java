package ru.sberstart.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.api.dto.user.request.UserCreationRequestDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserRolesRequestDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserStatusRequestDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserActiveResponseDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserCreationResponseDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserDeactivateResponseDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserRolesResponseDTO;
import ru.sberstart.finalproject.domain.enitity.user.User;

@Mapper(componentModel = "default", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserCreationRequestDTO userCreationDTO);

    User toEntity(UserStatusRequestDTO userStatusDTO);

    User toEntity(UserRolesRequestDTO userRolesDTO);

    UserActiveResponseDTO toUserActiveResponseDTO(User user);

    UserDeactivateResponseDTO toUserDeactivateResponseDTO(User user);

    UserRolesResponseDTO toUserRolesResponseDTO(User user);

    UserCreationResponseDTO toCreationResponseDTO(User user);
}
