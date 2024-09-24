package ru.sberstart.finalproject.bank.infrastructure.configuration;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberstart.finalproject.bank.api.BankController;
import ru.sberstart.finalproject.bank.application.service.BankService;
import ru.sberstart.finalproject.bank.infrastructure.repository.BankRepositoryImpl;

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
