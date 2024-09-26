package ru.sberstart.finalproject.application.service;

import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;
import ru.sberstart.finalproject.api.dto.user.response.UserActiveResponseDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserCreationRequestDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserCreationResponseDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserDeactivateResponseDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserRolesRequestDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserRolesResponseDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserStatusRequestDTO;
import ru.sberstart.finalproject.domain.enitity.user.User;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserStatus;
import ru.sberstart.finalproject.infrastructure.repostitory.implementation.UserRepositoryImpl;
import ru.sberstart.finalproject.mapper.UserMapper;

import java.util.Set;

public class UserService {
    private final UserRepositoryImpl repository;

    public UserService(UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public UserCreationResponseDTO createUser(UserCreationRequestDTO userCreationDTO) {
        User userRecord = UserMapper.INSTANCE.toEntity(userCreationDTO);
        userRecord.setStatus(UserStatus.REGISTERED);
        userRecord.setRoles(Set.of(UserRoles.USER));
        User user = repository.createUser(userRecord).orElseThrow(UnsuccessfulOperationException::new);
        return UserMapper.INSTANCE.toCreationResponseDTO(user);
    }

    public UserActiveResponseDTO approveUser(UserStatusRequestDTO userStatusDTO) {
        User userRecord = UserMapper.INSTANCE.toEntity(userStatusDTO);

        User user = executeDBTransaction(userRecord);

        return UserMapper.INSTANCE.toUserActiveResponseDTO(user);
    }

    public UserDeactivateResponseDTO blockUser(UserStatusRequestDTO userStatusDTO) {
        User userRecord = UserMapper.INSTANCE.toEntity(userStatusDTO);

        User user = executeDBTransaction(userRecord);

        return UserMapper.INSTANCE.toUserDeactivateResponseDTO(user);
    }

    public UserRolesResponseDTO changeUserRole(UserRolesRequestDTO userRolesDTO) {
        User userRecord = UserMapper.INSTANCE.toEntity(userRolesDTO);

        User user = repository.updateUserRoles(userRecord).orElseThrow(UnsuccessfulOperationException::new);

        return UserMapper.INSTANCE.toUserRolesResponseDTO(user);
    }

    private User executeDBTransaction(User userRecord) {
        return repository.updateUserFields(userRecord).orElseThrow(UnsuccessfulOperationException::new);
    }
}
