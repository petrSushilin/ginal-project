package ru.sberstart.finalproject.user.api.dto.request;

import ru.sberstart.finalproject.user.domain.entity.enums.UserRoles;

import java.util.Set;

public record UserRolesRequestDTO (String phoneNumber, Set<UserRoles> roles) {
}
