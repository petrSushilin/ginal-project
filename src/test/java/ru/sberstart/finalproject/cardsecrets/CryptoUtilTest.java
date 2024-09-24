package ru.sberstart.finalproject.cardsecrets;

import org.junit.jupiter.api.BeforeEach;
import ru.sberstart.finalproject.card.api.dto.inner.CardSecretInformationDTO;
import ru.sberstart.finalproject.card.api.dto.inner.CardSecretRecord;
import ru.sberstart.finalproject.card.application.utils.CryptoUtil;

import org.junit.Test;
import ru.sberstart.finalproject.global.exceptions.CryptoCodingException;

import static org.junit.Assert.*;

public class CryptoUtilTest {

    @BeforeEach
    public void setUpTests() {

    }


    /*
     * Тест шифрования данных
     */
    @Test
    public void testEncryptValidCardSecretInformationDto() {
        CryptoUtil cryptoUtil = new CryptoUtil();
        CardSecretInformationDTO dto = new CardSecretInformationDTO("John", "Doe", "12/23", "123");

        CardSecretRecord encryptedRecord = cryptoUtil.encrypt(dto);

        assertNotNull(encryptedRecord);
        assertNotEquals(dto.name(), encryptedRecord.name());
        assertNotEquals(dto.surname(), encryptedRecord.surname());
        assertNotEquals(dto.validatePeriod(), encryptedRecord.validatePeriod());
        assertNotEquals(dto.CVV(), encryptedRecord.CVV());
        assertNotNull(encryptedRecord.secretCode());
    }

    /*
     * Тест проведения валидности расшифрованных данных
     */
    @Test
    public void testDecryptValidCardSecretRecord() {
        CryptoUtil cryptoUtil = new CryptoUtil();
        CardSecretInformationDTO dto = new CardSecretInformationDTO("John", "Doe", "12/23", "123");
        CardSecretRecord encryptedRecord = cryptoUtil.encrypt(dto);

        CardSecretInformationDTO decryptedDto = cryptoUtil.decrypt(encryptedRecord);

        assertEquals(dto.name(), decryptedDto.name());
        assertEquals(dto.surname(), decryptedDto.surname());
        assertEquals(dto.validatePeriod(), decryptedDto.validatePeriod());
        assertEquals(dto.CVV(), decryptedDto.CVV());
    }

    @Test
    public void test_generate_cvv_length() {
        CryptoUtil cryptoUtil = new CryptoUtil();

        String cvv = cryptoUtil.generateCVV();

        assertNotNull(cvv);
        assertEquals(3, cvv.length());
        assertTrue(cvv.matches("\\d{3}"));
    }

    @Test
    public void test_encrypt_empty_card_secret_information_dto() {
        CryptoUtil cryptoUtil = new CryptoUtil();
        CardSecretInformationDTO dto = new CardSecretInformationDTO("", "", "", "");

        CardSecretRecord encryptedRecord = cryptoUtil.encrypt(dto);

        assertNotNull(encryptedRecord);
    }

    @Test
    public void test_decrypt_empty_card_secret_record() {
        CryptoUtil cryptoUtil = new CryptoUtil();
        CardSecretRecord record = new CardSecretRecord("", "", "", "", "");

        assertThrows(CryptoCodingException.class, () -> cryptoUtil.decrypt(record));
    }
}