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

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "roles", ignore = true)
//    @Mapping(target = "status", ignore = true)
    User toEntity(UserCreationRequestDTO userCreationDTO);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "name", ignore = true)
//    @Mapping(target = "surname", ignore = true)
//    @Mapping(target = "birthdate", ignore = true)
//    @Mapping(target = "passportNumber", ignore = true)
//    @Mapping(target = "roles", ignore = true)
    User toEntity(UserStatusRequestDTO userStatusDTO);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "name", ignore = true)
//    @Mapping(target = "surname", ignore = true)
//    @Mapping(target = "birthdate", ignore = true)
//    @Mapping(target = "passportNumber", ignore = true)
//    @Mapping(target = "status", ignore = true)
    User toEntity(UserRolesRequestDTO userRolesDTO);

    UserActiveResponseDTO toUserActiveResponseDTO(User user);

    UserDeactivateResponseDTO toUserDeactivateResponseDTO(User user);

    UserRolesResponseDTO toUserRolesResponseDTO(User user);

    UserCreationResponseDTO toCreationResponseDTO(User user);
}
