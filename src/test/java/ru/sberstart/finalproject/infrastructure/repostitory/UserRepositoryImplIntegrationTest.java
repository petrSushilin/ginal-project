package ru.sberstart.finalproject.infrastructure.repostitory;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.DatabaseInitializer;
import ru.sberstart.finalproject.domain.enitity.user.User;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserState;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserRepositoryImplIntegrationTest extends DatabaseInitializer {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser_ShouldInsertUserAndReturnCreatedUser() {
        // Данные для нового пользователя
        UUID userId = UUID.randomUUID();
        String userPhoneNumber = "9123456789";
        User newUser = new User.Builder()
                .withId(userId)
                .withName("Test")
                .withSurname("User")
                .withBirthdate(LocalDate.of(1980, 1, 1))
                .withPhoneNumber(userPhoneNumber)
                .withPassportNumber("4001123456")
                .withRoles(Set.of(UserRoles.USER))
                .withState(UserState.REGISTERED)
                .build();

        // Вызов метода createUser
        Optional<User> createdUser = userRepository.createUser(newUser);

        // Проверка, что пользователь был успешно создан и возвращен
        assertThat(createdUser).isPresent();
        assertThat(createdUser.get().getId()).isEqualTo(userId);
        assertThat(createdUser.get().getName()).isEqualTo("Test");
        assertThat(createdUser.get().getSurname()).isEqualTo("User");
        assertThat(createdUser.get().getBirthdate()).isEqualTo(LocalDate.of(1980, 1, 1));
        assertThat(createdUser.get().getPhoneNumber()).isEqualTo(userPhoneNumber);
        assertThat(createdUser.get().getPassportNumber()).isEqualTo("4001123456");
        assertThat(createdUser.get().getState()).isEqualTo(UserState.REGISTERED);

        // Дополнительная проверка: пользователь должен быть в базе данных
        Optional<User> foundUser = userRepository.getFullUserInfoByPhoneNumber(userPhoneNumber);
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Test");
    }

    @Test
    void updateUserFields() {
        // Данные для нового пользователя
        User user = new User.Builder()
                .withName("Test")
                .withSurname("User")
                .withBirthdate(LocalDate.of(1980, 1, 1))
                .withPhoneNumber("9123456789")
                .withPassportNumber("4001123456")
                .withRoles(Set.of(UserRoles.USER))
                .withState(UserState.REGISTERED)
                .build();

        // Вызов метода createUser
        Optional<User> createdUserOpt = userRepository.createUser(user);

        // Проверка, что пользователь был успешно создан и возвращен
        assertThat(createdUserOpt).isPresent();
        assertThat(createdUserOpt.get().getId()).isNotNull();

        // Изменение полей, доступных к обновлению (кроме ролей)
        user.setName("Test1");
        user.setSurname("User1");
        user.setBirthdate(LocalDate.of(2000, 12, 31));
        user.setPassportNumber("1234123456");
        user.setState(UserState.REGISTERED);

        // Обновление сущности и проверка получения сущности из БД
        Optional<User> updatedUserOpt = userRepository.updateUserFields(user);
        assertThat(updatedUserOpt).isPresent();

        // Проверка обновленной сущности из БД
        User updatedUser = updatedUserOpt.get();
        assertThat(updatedUser.getId()).isEqualTo(createdUserOpt.get().getId());
        assertThat(updatedUser.getName()).isEqualTo(user.getName());
        assertThat(updatedUser.getSurname()).isEqualTo(user.getSurname());
        assertThat(updatedUser.getBirthdate()).isEqualTo(user.getBirthdate());
        assertThat(updatedUser.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
        assertThat(updatedUser.getPassportNumber()).isEqualTo(user.getPassportNumber());
        assertThat(updatedUser.getState()).isEqualTo(user.getState());
    }

    @Test
    void updateUserRoles() {
        // Данные для нового пользователя
        User user = new User.Builder()
                .withName("Test")
                .withSurname("User")
                .withBirthdate(LocalDate.of(1980, 1, 1))
                .withPhoneNumber("9123456789")
                .withPassportNumber("4001123456")
                .withRoles(Set.of(UserRoles.USER))
                .withState(UserState.REGISTERED)
                .build();

        // Вызов метода createUser
        Optional<User> createdUserOpt = userRepository.createUser(user);

        // Проверка, что пользователь был успешно создан и возвращен
        assertThat(createdUserOpt).isPresent();
        assertThat(createdUserOpt.get().getId()).isNotNull();

        // Изменение ролей пользователя
        Set<UserRoles> roles = Set.of(UserRoles.MANAGER, UserRoles.ADMIN);
        user.setRoles(roles);

        // Обновление сущности и проверка получения сущности из БД
        Optional<User> updatedUserOpt = userRepository.updateUserRoles(user);
        assertThat(updatedUserOpt).isPresent();

        // Проверка данных сущности, обновленной в БД
        User updatedUser = updatedUserOpt.get();
        assertThat(updatedUser.getId()).isEqualTo(createdUserOpt.get().getId());
        // Данным юзер кейсом мы проверяем тот факт, что роли после обновления содержат только значения,
        // которые мы передавали в обновленной сущности, исключая тех, которые были в первоначальной сущности.
        assertThat(updatedUser.getRoles()).isEqualTo(roles);
    }

    @Test
    void getUserWithRoles() {
        // Данные для нового пользователя
        String userPhoneNumber = "9123456789";
        Set<UserRoles> roles = Set.of(UserRoles.USER, UserRoles.ADMIN);

        User newUser = new User.Builder()
                .withName("Test")
                .withSurname("User")
                .withBirthdate(LocalDate.of(1980, 1, 1))
                .withPhoneNumber(userPhoneNumber)
                .withPassportNumber("4001123456")
                .withRoles(roles)
                .withState(UserState.REGISTERED)
                .build();

        // Вызов метода createUser
        Optional<User> createdUser = userRepository.createUser(newUser);

        // Проверка, что пользователь был успешно создан и возвращен
        assertThat(createdUser).isPresent();
        UUID id = createdUser.get().getId();
        assertThat(id).isNotNull();

        // Дополнительная проверка: пользователь должен быть в базе данных
        Optional<User> foundUserOpt = userRepository.getFullUserInfoByPhoneNumber(userPhoneNumber);
        assertThat(foundUserOpt).isPresent();
        User foundUser = foundUserOpt.get();
        assertThat(foundUser.getId()).isEqualTo(id);
        assertThat(foundUser.getRoles()).isEqualTo(roles);
    }
}