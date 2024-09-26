package ru.sberstart.finalproject.domain.enitity.user;

import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserStatus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class User {
     private UUID id;
     private String name;
     private String surname;
     private LocalDate birthdate;
     private String phoneNumber;
     private String passportNumber;
     private Set<UserRoles> roles;
     private UserStatus status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
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

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public User(UUID id, String name, String surname, LocalDate birthdate, String phoneNumber, String passportNumber, Set<UserRoles> roles, UserStatus status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
        this.roles = roles;
        this.status = status;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String surname;
        private LocalDate birthdate;
        private String phoneNumber;
        private String passportNumber;
        private Set<UserRoles> roles;
        private UserStatus status;

        public User build() {
            return new User(id, name, surname, birthdate, phoneNumber, passportNumber, roles, status);
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

        public Builder withBirthdate(LocalDate birthdate) {
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

        public Builder withRoles(Set<UserRoles> roles) {
            this.roles = roles;
            return this;
        }

        public Builder withStatus(UserStatus status) {
            this.status = status;
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
                ", birthdate=" + birthdate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", roles=" + roles +
                ", status=" + status +
                '}';
    }
}
