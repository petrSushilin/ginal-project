package ru.sberstart.finalproject.infrastructure.repostitory;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import ru.sberstart.finalproject.domain.enitity.user.User;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserState;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.sberstart.finalproject.jooq.Tables.ROLES;
import static ru.sberstart.finalproject.jooq.Tables.USERROLES;
import static ru.sberstart.finalproject.jooq.Tables.USERS;

public class UserRepositoryImpl implements UserRepository {
    private DSLContext context;

    public UserRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    private final List<Field<?>> USER_FIELDS = List.of(
            USERS.ID,
            USERS.NAME,
            USERS.SURNAME,
            USERS.BIRTHDATE,
            USERS.PHONE_NUMBER,
            USERS.PASSPORT_NUMBER,
            ROLES.ROLE_NAME,
            USERS.STATUS
    );

    private static User buildUser(Result<Record> records) {
        Record record = records.get(0);

        Set<UserRoles> roles = records.stream()
                .map(r -> UserRoles.valueOf(r.get(ROLES.ROLE_NAME)))
                .collect(Collectors.toSet());

        return new User.Builder()
                .withId(record.get(USERS.ID))
                .withName(record.get(USERS.NAME))
                .withSurname(record.get(USERS.SURNAME))
                .withBirthdate(record.get(USERS.BIRTHDATE))
                .withPhoneNumber(record.get(USERS.PHONE_NUMBER))
                .withPassportNumber(record.get(USERS.PASSPORT_NUMBER))
                .withRoles(roles)
                .withState(UserState.valueOf(record.get(USERS.STATUS)))
                .build();
    }

    @Override
    public Optional<User> createUser(User user) {
        context.insertInto(USERS)
                .set(context.newRecord(USERS, user))
                .execute();

        setUpUserId(user);
        setUpUserRoles(user);

        return selectAfterExecute(user);
    }

    @Override
    public Optional<User> updateUserFields(User user) {
        int affectedRows = context
                .update(USERS)
                .set(context.newRecord(USERS, user))
                .where(USERS.PHONE_NUMBER.eq(user.getPhoneNumber()))
                .execute();

        if (affectedRows == 0) return Optional.empty();

        setUpUserId(user);

        return selectAfterExecute(user);
    }

    @Override
    public Optional<User> updateUserRoles(User user) {
        setUpUserId(user);

        context.deleteFrom(USERROLES)
                .where(USERROLES.USER_ID.eq(user.getId()))
                .execute();

        setUpUserRoles(user);

        return selectAfterExecute(user);
    }

    @Override
    public Optional<User> getFullUserInfoByPhoneNumber(String userPhoneNumber) {
        return context.select(USER_FIELDS)
                .from(USERS)
                .leftJoin(USERROLES)
                .on(USERS.ID.eq(USERROLES.USER_ID))
                .leftJoin(ROLES)
                .on(USERROLES.ROLE_ID.eq(ROLES.ID))
                .where(USERS.PHONE_NUMBER.eq(userPhoneNumber))
                .fetchGroups(USERS.ID)
                .values().stream()
                .map(UserRepositoryImpl::buildUser)
                .findFirst();
    }

    private void setUpUserRoles(User user) {
        Map<String, UUID> roleIds = context.select(ROLES.ROLE_NAME, ROLES.ID)
                .from(ROLES)
                .where(ROLES.ROLE_NAME.in(user.getRoles().stream().map(UserRoles::name).collect(Collectors.toList())))
                .fetchMap(ROLES.ROLE_NAME, ROLES.ID);

        if (!roleIds.isEmpty()) {
            context.batchInsert(
                    user.getRoles().stream()
                            .map(role -> context.newRecord(USERROLES)
                                    .values(user.getId(), roleIds.get(role.name())))
                            .collect(Collectors.toList())
            ).execute();
        }
    }

    private void setUpUserId(User user) {
        UUID userId = context.fetchOne("SELECT ID FROM USERS WHERE PHONE_NUMBER = ? ", user.getPhoneNumber())
                .get(USERS.ID);
        user.setId(userId);
    }

    private Optional<User> selectAfterExecute(User user) {
        return context.select(USER_FIELDS)
                .from(USERS)
                .leftJoin(USERROLES).on(USERS.ID.eq(USERROLES.USER_ID))
                .leftJoin(ROLES).on(USERROLES.ROLE_ID.eq(ROLES.ID))
                .where(USERS.ID.eq(user.getId()))
                .fetchGroups(USERS.ID)
                .values().stream()
                .map(UserRepositoryImpl::buildUser)
                .findFirst();
    }
}
