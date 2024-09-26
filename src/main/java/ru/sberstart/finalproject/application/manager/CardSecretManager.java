package ru.sberstart.finalproject.application.manager;

import ru.sberstart.finalproject.api.dto.bankaccount.inner.BankAccountCardIssueInformationDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;
import ru.sberstart.finalproject.api.dto.card.inner.CardSecretInformationDTO;
import ru.sberstart.finalproject.api.dto.card.inner.CardSecretRecord;
import ru.sberstart.finalproject.application.utils.CryptoUtil;
import ru.sberstart.finalproject.domain.enitity.card.CardSecret;
import ru.sberstart.finalproject.global.exceptions.CryptoCodingException;

import java.time.LocalDate;

public class CardSecretManager {
    private final CryptoUtil util;
    private final static int CARD_VALIDATE_PERIOD = 5;

    public CardSecretManager(CryptoUtil util) {
        this.util = util;
    }

    public CardSecret configureCardSecret(BankAccountCardIssueInformationDTO bankAccountInformation, String cardNumber) {
        CardSecretInformationDTO creationDTO = customizeCardSecret(bankAccountInformation, cardNumber);

        return encryptSecretCode(creationDTO);
    }

    public boolean isUnavailableCardIssue(BankAccountCardIssueInformationDTO bankAccountInformation) {
        return bankAccountInformation.state().equals(BankAccountStates.ACTIVE);
    }

    private CardSecretInformationDTO customizeCardSecret(BankAccountCardIssueInformationDTO bankAccountInformation, String cardNumber) {
        return new CardSecretInformationDTO(cardNumber, bankAccountInformation.userName(), bankAccountInformation.userSurname(),
                String.valueOf(LocalDate.now().withDayOfMonth(1).plusYears(CARD_VALIDATE_PERIOD)), util.generateCVV());
    }

    private CardSecret encryptSecretCode(CardSecretInformationDTO creationDTO) {
        CardSecret encryptedRecord = util.encrypt(creationDTO);

        CardSecretInformationDTO decryptedRecord = util.decrypt(encryptedRecord);

        if (!decryptedRecord.equals(creationDTO)) throw new CryptoCodingException();

        return encryptedRecord;
    }

    public CardSecretInformationDTO decryptSecretCode(CardSecret cardSecretRecord) {
        return util.decrypt(cardSecretRecord);
    }
}
