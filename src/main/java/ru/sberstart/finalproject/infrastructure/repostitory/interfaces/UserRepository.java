package ru.sberstart.finalproject.infrastructure.repostitory.interfaces;

import ru.sberstart.finalproject.domain.enitity.user.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> createUser(User user);

    Optional<User> updateUserFields(User user);

    Optional<User> updateUserRoles(User user);

    Optional<User> getFullUserInfoByPhoneNumber(String userPhoneNumber);
}
