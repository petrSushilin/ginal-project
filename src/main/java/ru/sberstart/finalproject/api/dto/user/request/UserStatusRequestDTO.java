package ru.sberstart.finalproject.api.dto.user.request;

import ru.sberstart.finalproject.domain.enitity.user.enums.UserStatus;

public record UserStatusRequestDTO (String phoneNumber, UserStatus status) {
}
