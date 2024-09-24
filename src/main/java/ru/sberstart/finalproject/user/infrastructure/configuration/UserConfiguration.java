package ru.sberstart.finalproject.user.infrastructure.configuration;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberstart.finalproject.user.api.UserController;
import ru.sberstart.finalproject.user.application.service.UserService;
import ru.sberstart.finalproject.user.infrastructure.repository.UserRepositoryImpl;

@Configuration
public class UserConfiguration {
    @Bean
    public UserRepositoryImpl userRepository (DSLContext context) {
        return new UserRepositoryImpl(context);
    }

    @Bean
    public UserService userService (UserRepositoryImpl userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public UserController userController (UserService userService) {
        return new UserController(userService);
    }
}
