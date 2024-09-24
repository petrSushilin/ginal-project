package ru.sberstart.finalproject.user.api.dto.response;

import ru.sberstart.finalproject.user.domain.entity.enums.UserRoles;
import ru.sberstart.finalproject.user.domain.entity.enums.UserStatus;

import java.util.Set;

public record UserActiveResponseDTO (String name, String surname, String phoneNumber, Set<UserRoles> roles, UserStatus Status) {
}
