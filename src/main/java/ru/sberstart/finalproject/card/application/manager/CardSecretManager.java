package ru.sberstart.finalproject.card.application.manager;

import ru.sberstart.finalproject.bankaccount.api.dto.inner.BankAccountCardIssueInformationDTO;
import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;
import ru.sberstart.finalproject.card.api.dto.inner.CardSecretInformationDTO;
import ru.sberstart.finalproject.card.api.dto.inner.CardSecretRecord;
import ru.sberstart.finalproject.card.application.utils.CryptoUtil;
import ru.sberstart.finalproject.global.exceptions.CryptoCodingException;

import java.time.LocalDate;

public class CardSecretManager {
    private final CryptoUtil util;
    private final static int CARD_VALIDATE_PERIOD = 5;

    public CardSecretManager(CryptoUtil util) {
        this.util = util;
    }

    public CardSecretRecord configureCardSecret(BankAccountCardIssueInformationDTO bankAccountInformation) {
        CardSecretInformationDTO creationDTO = customizeCardSecret(bankAccountInformation);

        return encryptSecretCode(creationDTO);
    }

    public boolean isUnavailableCardIssue(BankAccountCardIssueInformationDTO bankAccountInformation) {
        return bankAccountInformation.state().equals(BankAccountStates.ACTIVE);
    }

    public boolean isUnsuccessfulRegisterCardSecret() {
        return false;
    }

    private CardSecretInformationDTO customizeCardSecret(BankAccountCardIssueInformationDTO bankAccountInformation) {
        return new CardSecretInformationDTO(bankAccountInformation.userName(), bankAccountInformation.userSurname(),
                String.valueOf(LocalDate.now().withDayOfMonth(1).plusYears(CARD_VALIDATE_PERIOD)), util.generateCVV());
    }

    private CardSecretRecord encryptSecretCode(CardSecretInformationDTO creationDTO) {
        CardSecretRecord encryptedRecord = util.encrypt(creationDTO);

        CardSecretInformationDTO decryptedRecord = util.decrypt(encryptedRecord);

        if (!decryptedRecord.equals(creationDTO)) throw new CryptoCodingException();

        return encryptedRecord;
    }
}
