package ru.sberstart.finalproject.api.dto.user.response;

import ru.sberstart.finalproject.domain.enitity.user.enums.UserStatus;

public record UserDeactivateResponseDTO (String phoneNumber, UserStatus status) {
}
