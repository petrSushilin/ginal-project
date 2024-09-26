package ru.sberstart.finalproject.api.dto.user.request;

public record UserCreationRequestDTO (String name, String surname, String birthdate, String phoneNumber, String passportNumber) {
}
