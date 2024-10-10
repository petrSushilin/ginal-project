package ru.sberstart.finalproject.infrastructure.configuration;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberstart.finalproject.api.controller.BankController;
import ru.sberstart.finalproject.application.service.BankService;
import ru.sberstart.finalproject.infrastructure.repostitory.BankRepositoryImpl;

@Configuration
public class BankConfiguration {
    @Bean
    public BankRepositoryImpl bankRepository(DSLContext context) {
        return new BankRepositoryImpl(context);
    }

    @Bean
    public BankService bankService(BankRepositoryImpl bankRepository) {
        return new BankService(bankRepository);
    }

    @Bean
    public BankController bankController(BankService bankService) {
        return new BankController(bankService);
    }
}
