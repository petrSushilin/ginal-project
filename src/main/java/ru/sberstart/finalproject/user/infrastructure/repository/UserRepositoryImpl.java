package ru.sberstart.finalproject.user.infrastructure.repository;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import ru.sberstart.finalproject.user.domain.entity.User;
import ru.sberstart.finalproject.user.domain.entity.enums.UserRoles;
import ru.sberstart.finalproject.user.domain.entity.enums.UserStatus;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
                .withPhoneNumber(record.get(USERS.PHONE_NUMBER))
                .withPassportNumber(record.get(USERS.PASSPORT_NUMBER))
                .withRoles(roles)
                .withStatus(UserStatus.valueOf(record.get(USERS.STATUS)))
                .build();
    }

    @Override
    public Optional<User> createUser(User user) {
        UUID userId = Objects.requireNonNull(context
                        .insertInto(USERS)
                        .set(context.newRecord(USERS, user))
                        .returningResult(USERS.ID)
                        .fetchOne())
                .get(USERS.ID);

        Map<String, UUID> roleIds = context.select(ROLES.ROLE_NAME, ROLES.ID)
                .from(ROLES)
                .where(ROLES.ROLE_NAME.in(user.getRoles().stream().map(UserRoles::name).collect(Collectors.toList())))
                .fetchMap(ROLES.ROLE_NAME, ROLES.ID);

        context.batchInsert(
                user.getRoles().stream()
                        .map(role -> context.newRecord(USERROLES)
                                .values(userId, roleIds.get(role.name()))
                        )
                        .collect(Collectors.toList())
        ).execute();

        return context.select(USER_FIELDS)
                .from(USERS)
                .leftJoin(USERROLES).on(USERS.ID.eq(USERROLES.USER_ID))
                .leftJoin(ROLES).on(USERROLES.ROLE_ID.eq(ROLES.ID))
                .where(USERS.ID.eq(userId))
                .fetchGroups(USERS.ID)
                .values().stream()
                .map(UserRepositoryImpl::buildUser)
                .findFirst();
    }

    @Override
    public Optional<User> updateUserFields(User user) {
        Record record = context
                .update(USERS)
                .set(context.newRecord(USERS, user))
                .where(USERS.PHONE_NUMBER.eq(user.getPhoneNumber()))
                .returning(USER_FIELDS)
                .fetchOne();

        assert record != null;

        return context.select(USER_FIELDS)
                .from(USERS)
                .leftJoin(USERROLES).on(USERS.ID.eq(USERROLES.USER_ID))
                .leftJoin(ROLES).on(USERROLES.ROLE_ID.eq(ROLES.ID))
                .where(USERS.ID.eq(record.get(USERS.ID)))
                .fetchGroups(USERS.ID)
                .values().stream()
                .map(UserRepositoryImpl::buildUser)
                .findFirst();
    }

    @Override
    public Optional<User> updateUserRoles(User user) {
        context.deleteFrom(USERROLES)
                .where(USERROLES.USER_ID.eq(user.getId()))
                .execute();

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

    @Override
    public Optional<User> getUserWithRoles(String userPhoneNumber) {
        return context.select(USER_FIELDS)
                .from(USERS)
                .leftJoin(USERROLES)
                .on(USERS.ID.eq(USERROLES.USER_ID))
                .leftJoin(ROLES)
                .on(USERROLES.ROLE_ID.eq(ROLES.ID))
                .fetchGroups(USERS.ID)
                .values().stream()
                .map(UserRepositoryImpl::buildUser)
                .findFirst();
    }
}
