package ru.sberstart.finalproject.card.application.service;

import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.bankaccount.api.dto.inner.BankAccountCardIssueInformationDTO;
import ru.sberstart.finalproject.bankaccount.application.service.BankAccountService;
import ru.sberstart.finalproject.card.api.dto.inner.CardCustomizingDTO;
import ru.sberstart.finalproject.card.api.dto.inner.CardSecretRecord;
import ru.sberstart.finalproject.card.application.manager.CardSecretManager;
import ru.sberstart.finalproject.card.domain.entity.CardSecret;
import ru.sberstart.finalproject.card.infrastructure.repository.CardSecretRepositoryImpl;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;
import ru.sberstart.finalproject.global.exceptions.RecordNotFoundException;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;

import java.util.Optional;
import java.util.UUID;

public class CardSecretService {
    private final CardSecretManager manager;
    private final BankAccountService service;
    private final CardSecretRepositoryImpl repository;

    public CardSecretService(CardSecretManager manager, BankAccountService service, CardSecretRepositoryImpl repository) {
        this.manager = manager;
        this.service = service;
        this.repository = repository;
    }

    @Transactional
    public CardCustomizingDTO register(String number) {
        // проверяем наличие банковского аккаунта
        BankAccountCardIssueInformationDTO bankAccountInformation = service.getBankAccountCardIssueInformationByNumber(number);

        if (manager.isUnavailableCardIssue(bankAccountInformation)) throw new NotAvailableActionsException();

        // генерируем секретный код и информацию о пользователе, вся информация возвращается в засекреченном виде
        CardSecretRecord cardSecretRecord = manager.configureCardSecret(bankAccountInformation);

        // добавляем запись в БД
        CardSecret cardSecret = repository.create(cardSecretRecord).orElseThrow(UnsuccessfulOperationException::new);

        CardCustomizingDTO cardCustomizingDTO = new CardCustomizingDTO();
        cardCustomizingDTO.setBankAccountId(bankAccountInformation.id());
        cardCustomizingDTO.setBankId(bankAccountInformation.bankId());
        cardCustomizingDTO.setCardSecretId(cardSecret.id());

        // возвращаем необходимые сведения на слой карты
        return cardCustomizingDTO;
    }

    public CardSecret getCardSecret(String cardNumber) {
        Optional<CardSecret> cardSecretRecord = repository.getByNumber(cardNumber);

        return cardSecretRecord.orElseThrow(RecordNotFoundException::new);
    }
}
