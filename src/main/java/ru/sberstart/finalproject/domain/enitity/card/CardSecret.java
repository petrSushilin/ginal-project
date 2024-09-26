package ru.sberstart.finalproject.domain.enitity.card;

import java.time.LocalDate;
import java.util.UUID;

public record CardSecret (String cardNumber, String holderName, String holderSurname, String validatePeriod, String CVV, String secretCode) {
    public static class Builder {
        private String cardNumber;
        private String holderName;
        private String holderSurname;
        private String validatePeriod;
        private String CVV;
        private String secretCode;

        public Builder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder withHolderName(String holderName) {
            this.holderName = holderName;
            return this;
        }

        public Builder withHolderSurname(String holderSurname) {
            this.holderSurname = holderSurname;
            return this;
        }

        public Builder withValidatePeriod(String validatePeriod) {
            this.validatePeriod = validatePeriod;
            return this;
        }

        public Builder withCVV(String CVV) {
            this.CVV = CVV;
            return this;
        }

        public Builder withSecretCode(String secretCode) {
            this.secretCode = secretCode;
            return this;
        }

        public CardSecret build() {
            return new CardSecret(cardNumber, holderName, holderSurname, validatePeriod, CVV, secretCode);
        }
    }
}
