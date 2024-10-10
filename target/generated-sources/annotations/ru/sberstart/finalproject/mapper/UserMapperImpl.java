package ru.sberstart.finalproject.mapper;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import ru.sberstart.finalproject.api.dto.user.request.UserCreationRequestDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserRolesRequestDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserStatusRequestDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserActiveResponseDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserCreationResponseDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserDeactivateResponseDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserRolesResponseDTO;
import ru.sberstart.finalproject.domain.enitity.user.User;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserRoles;
import ru.sberstart.finalproject.domain.enitity.user.enums.UserState;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T08:57:06+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserCreationRequestDTO userCreationDTO) {
        if ( userCreationDTO == null ) {
            return null;
        }

        String name = null;
        String surname = null;
        LocalDate birthdate = null;
        String passportNumber = null;
        String phoneNumber = null;

        name = userCreationDTO.name();
        surname = userCreationDTO.surname();
        if ( userCreationDTO.birthdate() != null ) {
            birthdate = LocalDate.parse( userCreationDTO.birthdate() );
        }
        passportNumber = userCreationDTO.passportNumber();
        phoneNumber = userCreationDTO.phoneNumber();

        UUID id = null;
        Set<UserRoles> roles = null;
        UserState status = null;

        User user = new User( id, name, surname, birthdate, phoneNumber, passportNumber, roles, status );

        return user;
    }

    @Override
    public User toEntity(UserStatusRequestDTO userStatusDTO) {
        if ( userStatusDTO == null ) {
            return null;
        }

        UserState status = null;
        String phoneNumber = null;

        status = userStatusDTO.status();
        phoneNumber = userStatusDTO.phoneNumber();

        UUID id = null;
        String name = null;
        String surname = null;
        LocalDate birthdate = null;
        String passportNumber = null;
        Set<UserRoles> roles = null;

        User user = new User( id, name, surname, birthdate, phoneNumber, passportNumber, roles, status );

        return user;
    }

    @Override
    public User toEntity(UserRolesRequestDTO userRolesDTO) {
        if ( userRolesDTO == null ) {
            return null;
        }

        Set<UserRoles> roles = null;
        String phoneNumber = null;

        Set<UserRoles> set = userRolesDTO.roles();
        if ( set != null ) {
            roles = new LinkedHashSet<UserRoles>( set );
        }
        phoneNumber = userRolesDTO.phoneNumber();

        UUID id = null;
        String name = null;
        String surname = null;
        LocalDate birthdate = null;
        String passportNumber = null;
        UserState status = null;

        User user = new User( id, name, surname, birthdate, phoneNumber, passportNumber, roles, status );

        return user;
    }

    @Override
    public UserActiveResponseDTO toUserActiveResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        String surname = null;
        String phoneNumber = null;
        Set<UserRoles> roles = null;

        name = user.getName();
        surname = user.getSurname();
        phoneNumber = user.getPhoneNumber();
        Set<UserRoles> set = user.getRoles();
        if ( set != null ) {
            roles = new LinkedHashSet<UserRoles>( set );
        }

        UserState status = null;

        UserActiveResponseDTO userActiveResponseDTO = new UserActiveResponseDTO( name, surname, phoneNumber, roles, status );

        return userActiveResponseDTO;
    }

    @Override
    public UserDeactivateResponseDTO toUserDeactivateResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String phoneNumber = null;
        UserState status = null;

        phoneNumber = user.getPhoneNumber();
        status = user.getState();

        UserDeactivateResponseDTO userDeactivateResponseDTO = new UserDeactivateResponseDTO( phoneNumber, status );

        return userDeactivateResponseDTO;
    }

    @Override
    public UserRolesResponseDTO toUserRolesResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String phoneNumber = null;
        Set<UserRoles> roles = null;

        phoneNumber = user.getPhoneNumber();
        Set<UserRoles> set = user.getRoles();
        if ( set != null ) {
            roles = new LinkedHashSet<UserRoles>( set );
        }

        UserRolesResponseDTO userRolesResponseDTO = new UserRolesResponseDTO( phoneNumber, roles );

        return userRolesResponseDTO;
    }

    @Override
    public UserCreationResponseDTO toCreationResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        String surname = null;
        String phoneNumber = null;

        name = user.getName();
        surname = user.getSurname();
        phoneNumber = user.getPhoneNumber();

        UserCreationResponseDTO userCreationResponseDTO = new UserCreationResponseDTO( name, surname, phoneNumber );

        return userCreationResponseDTO;
    }
}
