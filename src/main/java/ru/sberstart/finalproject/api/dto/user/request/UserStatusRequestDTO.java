package ru.sberstart.finalproject.api.dto.user.request;

import ru.sberstart.finalproject.domain.enitity.user.enums.UserState;

public record UserStatusRequestDTO (String phoneNumber, UserState status) {
}
