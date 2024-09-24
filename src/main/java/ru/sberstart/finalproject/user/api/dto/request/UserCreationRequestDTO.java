package ru.sberstart.finalproject.user.api.dto.request;

public record UserCreationRequestDTO (String name, String surname, String birthdate, String phoneNumber, String passportNumber) {
}
