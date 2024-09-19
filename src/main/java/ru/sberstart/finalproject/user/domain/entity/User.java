package ru.sberstart.finalproject.user.domain.entity;

import java.util.Objects;
import java.util.UUID;

public class User {
     private UUID id;
     private String name;
     private String surname;
     private String birthdate;
     private String phoneNumber;
     private String passportNumber;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    private User(UUID id, String name, String surname, String birthdate, String phoneNumber, String passportNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String surname;
        private String birthdate;
        private String phoneNumber;
        private String passportNumber;

        public User build() {
            return new User(id, name, surname, birthdate, phoneNumber, passportNumber);
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withBirthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withPassportNumber(String passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(birthdate, user.birthdate) && Objects.equals(passportNumber, user.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthdate, passportNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
