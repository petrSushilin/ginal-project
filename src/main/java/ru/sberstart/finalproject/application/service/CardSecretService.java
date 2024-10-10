package ru.sberstart.finalproject.application.service;

import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.api.dto.bankaccount.inner.BankAccountCardIssueInformationDTO;
import ru.sberstart.finalproject.api.dto.card.inner.CardCustomizingDTO;
import ru.sberstart.finalproject.api.dto.card.inner.CardSecretInformationDTO;
import ru.sberstart.finalproject.application.manager.CardSecretManager;
import ru.sberstart.finalproject.domain.enitity.card.CardSecret;
import ru.sberstart.finalproject.infrastructure.repostitory.CardSecretRepositoryImpl;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;
import ru.sberstart.finalproject.global.exceptions.RecordNotFoundException;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;

import java.util.Optional;

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
    public CardCustomizingDTO register(String bankAccountNumber, String cardNumber) {
        // проверяем наличие банковского аккаунта
        BankAccountCardIssueInformationDTO bankAccountInformation = service.getBankAccountCardIssueInformationByNumber(bankAccountNumber);

        if (manager.isUnavailableCardIssue(bankAccountInformation)) throw new NotAvailableActionsException();


        System.out.println(bankAccountInformation);
        // генерируем секретный код и информацию о пользователе, вся информация возвращается в засекреченном виде
        CardSecret cardSecretRecord = manager.configureCardSecret(bankAccountInformation, cardNumber);
        // добавляем запись в БД
        System.out.println(cardSecretRecord);
        CardSecret cardSecret = repository.create(cardSecretRecord).orElseThrow(UnsuccessfulOperationException::new);

        CardCustomizingDTO cardCustomizingDTO = new CardCustomizingDTO();
        cardCustomizingDTO.setBankAccountId(bankAccountInformation.id());
        cardCustomizingDTO.setCardNumber(cardSecret.cardNumber());

        // возвращаем необходимые сведения на слой карты
        return cardCustomizingDTO;
    }

    public CardSecret getCardSecret(String cardNumber) {
        Optional<CardSecret> cardSecretRecord = repository.getByNumber(cardNumber);
        return cardSecretRecord.orElseThrow(RecordNotFoundException::new);
    }

    public CardSecretInformationDTO getDecryptCardSecret(String cardNumber) {
        CardSecret cardSecret = getCardSecret(cardNumber);
        return manager.decryptSecretCode(cardSecret);
    }
}
