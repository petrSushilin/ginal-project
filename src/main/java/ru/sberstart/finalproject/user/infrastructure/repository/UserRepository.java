package ru.sberstart.finalproject.user.infrastructure.repository;

import ru.sberstart.finalproject.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> createUser(User user);

    Optional<User> updateUserFields(User user);

    Optional<User> updateUserRoles(User user);

    Optional<User> getUserWithRoles(String userPhoneNumber);
}
