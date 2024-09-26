package ru.sberstart.finalproject.api.dto.card.response;

import java.util.UUID;

public record CardActiveResponseDTO (UUID bankAccountId, String number) {
}
