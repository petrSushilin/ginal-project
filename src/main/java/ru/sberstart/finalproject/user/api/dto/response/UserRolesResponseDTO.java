package ru.sberstart.finalproject.user.api.dto.response;

import ru.sberstart.finalproject.user.domain.entity.enums.UserRoles;

import java.util.Set;

public record UserRolesResponseDTO (String phoneNumber, Set<UserRoles> roles) {
}
