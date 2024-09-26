package ru.sberstart.finalproject.api.dto.user.request;

import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;

import java.util.Set;

public record UserRolesRequestDTO (String phoneNumber, Set<UserRoles> roles) {
}
