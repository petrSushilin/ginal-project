package ru.sberstart.finalproject.card.api.dto.response;

import java.util.UUID;

public record CardActiveResponseDTO (UUID bankAccountId, String number) {
}
