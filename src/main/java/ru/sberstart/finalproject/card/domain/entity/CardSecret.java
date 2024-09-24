package ru.sberstart.finalproject.card.domain.entity;

import java.time.LocalDate;
import java.util.UUID;

public record CardSecret (UUID id, String holderName, String holderSurname, LocalDate validatePeriod, String CVV, String secretCode) {
    public static class Builder {
        private UUID id;
        private String holderName;
        private String holderSurname;
        private LocalDate validatePeriod;
        private String CVV;
        private String secretCode;

        public Builder withId(UUID id) {
            this.id = id;
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

        public Builder withValidatePeriod(LocalDate validatePeriod) {
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
            return new CardSecret(id, holderName, holderSurname, validatePeriod, CVV, secretCode);
        }
    }
}
