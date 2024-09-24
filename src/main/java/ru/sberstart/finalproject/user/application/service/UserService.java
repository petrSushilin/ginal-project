package ru.sberstart.finalproject.user.application.service;

import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;
import ru.sberstart.finalproject.user.api.dto.response.UserActiveResponseDTO;
import ru.sberstart.finalproject.user.api.dto.request.UserCreationRequestDTO;
import ru.sberstart.finalproject.user.api.dto.response.UserCreationResponseDTO;
import ru.sberstart.finalproject.user.api.dto.response.UserDeactivateResponseDTO;
import ru.sberstart.finalproject.user.api.dto.request.UserRolesRequestDTO;
import ru.sberstart.finalproject.user.api.dto.response.UserRolesResponseDTO;
import ru.sberstart.finalproject.user.api.dto.request.UserStatusRequestDTO;
import ru.sberstart.finalproject.user.domain.entity.User;
import ru.sberstart.finalproject.user.domain.entity.enums.UserRoles;
import ru.sberstart.finalproject.user.domain.entity.enums.UserStatus;
import ru.sberstart.finalproject.user.infrastructure.repository.UserRepositoryImpl;
import ru.sberstart.finalproject.user.mapper.UserMapper;

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
        repository.createUser(userRecord).orElseThrow(UnsuccessfulOperationException::new);
        return null;
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

        User user = executeDBTransaction(userRecord);

        return UserMapper.INSTANCE.toUserRolesResponseDTO(user);
    }

    private User executeDBTransaction(User userRecord) {
        return repository.updateUserFields(userRecord).orElseThrow(UnsuccessfulOperationException::new);
    }
}
