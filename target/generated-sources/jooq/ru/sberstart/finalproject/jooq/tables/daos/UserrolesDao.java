/*
 * This file is generated by jOOQ.
 */
package ru.sberstart.finalproject.jooq.tables.daos;


import java.util.List;
import java.util.UUID;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;

import ru.sberstart.finalproject.jooq.tables.Userroles;
import ru.sberstart.finalproject.jooq.tables.records.UserrolesRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserrolesDao extends DAOImpl<UserrolesRecord, ru.sberstart.finalproject.jooq.tables.pojos.Userroles, Record2<UUID, UUID>> {

    /**
     * Create a new UserrolesDao without any configuration
     */
    public UserrolesDao() {
        super(Userroles.USERROLES, ru.sberstart.finalproject.jooq.tables.pojos.Userroles.class);
    }

    /**
     * Create a new UserrolesDao with an attached configuration
     */
    public UserrolesDao(Configuration configuration) {
        super(Userroles.USERROLES, ru.sberstart.finalproject.jooq.tables.pojos.Userroles.class, configuration);
    }

    @Override
    public Record2<UUID, UUID> getId(ru.sberstart.finalproject.jooq.tables.pojos.Userroles object) {
        return compositeKeyRecord(object.getUserId(), object.getRoleId());
    }

    /**
     * Fetch records that have <code>USER_ID BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<ru.sberstart.finalproject.jooq.tables.pojos.Userroles> fetchRangeOfUserId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Userroles.USERROLES.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>USER_ID IN (values)</code>
     */
    public List<ru.sberstart.finalproject.jooq.tables.pojos.Userroles> fetchByUserId(UUID... values) {
        return fetch(Userroles.USERROLES.USER_ID, values);
    }

    /**
     * Fetch records that have <code>ROLE_ID BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<ru.sberstart.finalproject.jooq.tables.pojos.Userroles> fetchRangeOfRoleId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Userroles.USERROLES.ROLE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>ROLE_ID IN (values)</code>
     */
    public List<ru.sberstart.finalproject.jooq.tables.pojos.Userroles> fetchByRoleId(UUID... values) {
        return fetch(Userroles.USERROLES.ROLE_ID, values);
    }
}
