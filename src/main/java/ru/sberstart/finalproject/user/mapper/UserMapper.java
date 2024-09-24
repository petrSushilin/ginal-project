package ru.sberstart.finalproject.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.sberstart.finalproject.user.api.dto.request.UserCreationRequestDTO;
import ru.sberstart.finalproject.user.api.dto.request.UserRolesRequestDTO;
import ru.sberstart.finalproject.user.api.dto.request.UserStatusRequestDTO;
import ru.sberstart.finalproject.user.api.dto.response.UserActiveResponseDTO;
import ru.sberstart.finalproject.user.api.dto.response.UserDeactivateResponseDTO;
import ru.sberstart.finalproject.user.api.dto.response.UserRolesResponseDTO;
import ru.sberstart.finalproject.user.domain.entity.User;

@Mapper(componentModel = "userService")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserCreationRequestDTO userCreationDTO);

    User toEntity(UserStatusRequestDTO userStatusDTO);

    User toEntity(UserRolesRequestDTO userRolesDTO);

    UserActiveResponseDTO toUserActiveResponseDTO(User user);

    UserDeactivateResponseDTO toUserDeactivateResponseDTO(User user);

    UserRolesResponseDTO toUserRolesResponseDTO(User user);
}
