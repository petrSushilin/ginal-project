package ru.sberstart.finalproject.domain.enitity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.sberstart.finalproject.domain.enitity.interfaces.Stateful;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserState;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles.USER;

public class User implements Stateful {
     private UUID id;
     private String name;
     private String surname;
     private LocalDate birthdate;
     private String phoneNumber;
     private String passportNumber;
     private Set<UserRoles> roles;

     @JsonProperty(defaultValue = "REGISTERED")
     private UserState state;

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
        if (roles == null) this.roles = Set.of(USER);
        else this.roles = roles;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        if (state == null) this.state = UserState.REGISTERED;
        else this.state = state;
    }

    public User(UUID id, String name, String surname, LocalDate birthdate, String phoneNumber, String passportNumber, Set<UserRoles> roles, UserState status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
        setRoles(roles);
        setState(status);
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String surname;
        private LocalDate birthdate;
        private String phoneNumber;
        private String passportNumber;
        private Set<UserRoles> roles;
        private UserState state;

        public User build() {
            return new User(id, name, surname, birthdate, phoneNumber, passportNumber, roles, state);
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

        public Builder withState(UserState state) {
            this.state = state;
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
                ", status=" + state +
                '}';
    }
}
