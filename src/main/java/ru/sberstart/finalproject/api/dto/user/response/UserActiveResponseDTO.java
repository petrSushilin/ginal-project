package ru.sberstart.finalproject.api.dto.user.response;

import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserState;

import java.util.Set;

public record UserActiveResponseDTO (String name, String surname, String phoneNumber, Set<UserRoles> roles, UserState Status) {
}
