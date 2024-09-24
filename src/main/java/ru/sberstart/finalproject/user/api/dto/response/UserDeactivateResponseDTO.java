package ru.sberstart.finalproject.user.api.dto.response;

import ru.sberstart.finalproject.user.domain.entity.enums.UserStatus;

public record UserDeactivateResponseDTO (String phoneNumber, UserStatus status) {
}
