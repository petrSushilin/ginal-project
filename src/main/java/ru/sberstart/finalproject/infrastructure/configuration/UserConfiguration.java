package ru.sberstart.finalproject.infrastructure.configuration;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberstart.finalproject.api.controller.UserController;
import ru.sberstart.finalproject.application.service.UserService;
import ru.sberstart.finalproject.infrastructure.repostitory.UserRepositoryImpl;

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
