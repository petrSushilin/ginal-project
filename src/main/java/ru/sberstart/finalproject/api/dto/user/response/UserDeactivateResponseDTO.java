package ru.sberstart.finalproject.api.dto.user.response;

import ru.sberstart.finalproject.domain.enitity.user.enums.UserState;

public record UserDeactivateResponseDTO (String phoneNumber, UserState status) {
}
