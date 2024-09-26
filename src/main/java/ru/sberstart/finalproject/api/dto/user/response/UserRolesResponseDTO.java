package ru.sberstart.finalproject.api.dto.user.response;

import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;

import java.util.Set;

public record UserRolesResponseDTO (String phoneNumber, Set<UserRoles> roles) {
}
