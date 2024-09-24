package ru.sberstart.finalproject.user.api.dto.request;

import ru.sberstart.finalproject.user.domain.entity.enums.UserStatus;

public record UserStatusRequestDTO (String phoneNumber, UserStatus status) {
}
