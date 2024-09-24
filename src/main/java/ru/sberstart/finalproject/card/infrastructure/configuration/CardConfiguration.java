package ru.sberstart.finalproject.card.infrastructure.configuration;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberstart.finalproject.bankaccount.application.service.BankAccountService;
import ru.sberstart.finalproject.card.api.CardController;
import ru.sberstart.finalproject.card.application.manager.CardManager;
import ru.sberstart.finalproject.card.application.manager.CardSecretManager;
import ru.sberstart.finalproject.card.application.service.CardSecretService;
import ru.sberstart.finalproject.card.application.service.CardService;
import ru.sberstart.finalproject.card.application.utils.CryptoUtil;
import ru.sberstart.finalproject.card.infrastructure.repository.CardRepositoryImpl;
import ru.sberstart.finalproject.card.infrastructure.repository.CardSecretRepositoryImpl;

@Configuration
public class CardConfiguration {
    @Bean
    public CardSecretRepositoryImpl cardSecretRepository(DSLContext context) {
        return new CardSecretRepositoryImpl(context);
    }

    @Bean
    public CryptoUtil cryptoUtil() {
        return new CryptoUtil();
    }

    @Bean
    public CardSecretManager cardSecretManager(CryptoUtil cryptoUtil) {
        return new CardSecretManager(cryptoUtil);
    }

    @Bean
    public CardSecretService cardSecretService(CardSecretManager cardSecretManager, BankAccountService bankAccountService, CardSecretRepositoryImpl cardSecretRepository) {
        return new CardSecretService(cardSecretManager, bankAccountService, cardSecretRepository);
    }

    @Bean
    public CardRepositoryImpl cardRepository(DSLContext context) {
        return new CardRepositoryImpl(context);
    }

    @Bean
    public CardManager cardManager() {
        return new CardManager();
    }

    @Bean
    public CardService cardService(CardSecretService cardSecretService, CardManager cardManager, CardRepositoryImpl cardRepository) {
        return new CardService(cardSecretService, cardManager, cardRepository);
    }

    @Bean
    public CardController cardController(CardService cardService) {
        return new CardController(cardService);
    }
}
