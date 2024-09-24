package ru.sberstart.finalproject.card.application.utils;

import ru.sberstart.finalproject.card.api.dto.inner.CardSecretInformationDTO;
import ru.sberstart.finalproject.card.api.dto.inner.CardSecretRecord;
import ru.sberstart.finalproject.global.exceptions.CryptoCodingException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Утилитный класс для шифрования и дешифрования данных карты:
 * - CVV-код
 * - Имя
 * - Фамилия
 * - Срок действия карты
 *
 * Использует AES-256 в режиме CBC с PKCS5Padding.
 */
public class CryptoUtil {
    private static final String AES = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16; // 16 байт для AES
    private static final int CVV_MIN = 100;
    private static final int CVV_MAX = 999;

    private SecretKey secretKey;
    private final SecureRandom secureRandom;

    /**
     * Конструктор. Генерирует новый секретный ключ при создании экземпляра.
     */
    public CryptoUtil() {
        this.secureRandom = new SecureRandom();
        this.secretKey = generateSecretKey();
    }

    /**
     * Публичный метод для шифрования данных карты.
     *
     * @param record Объект с данными карты.
     * @return Зашифрованный объект данных карты.
     */
    public CardSecretRecord encrypt(CardSecretInformationDTO record) {
        try {
            return new CardSecretRecord(
                    encryptField(record.name()),
                    encryptField(record.surname()),
                    encryptField(record.validatePeriod()),
                    encryptField(record.CVV()),
                    getSecretKey()
            );
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка при шифровании данных карты: " + ex.getMessage(), ex);
        }
    }

    /**
     * Публичный метод для дешифрования данных карты.
     *
     * @param record Зашифрованный объект данных карты.
     * @return Расшифрованный объект данных карты.
     */
    public CardSecretInformationDTO decrypt(CardSecretRecord record) {
        try {
            secretKey = decodeSecretKey(record.secretCode());
            return new CardSecretInformationDTO(
                    decryptField(record.name()),
                    decryptField(record.surname()),
                    decryptField(record.validatePeriod()),
                    decryptField(record.CVV())
            );
        } catch (Exception ex) {
            throw new CryptoCodingException("Ошибка при дешифровании данных карты: " + ex.getMessage());
        }
    }

    /**
     * Публичный метод для получения текущего секретного ключа.
     * После вызова метода ключ обновляется.
     *
     * @return Секретный ключ в формате Base64.
     */
    private String getSecretKey() {
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        // Обновляем секретный ключ после его получения
        this.secretKey = generateSecretKey();
        return encodedKey;
    }

    /**
     * Приватный метод для генерации нового секретного ключа AES-256.
     *
     * @return Новый секретный ключ.
     */
    private SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(256); // AES-256
            return keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка генерации секретного ключа: " + e.getMessage(), e);
        }
    }

    private SecretKey decodeSecretKey(String secretKey) {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, AES);
    }


    /**
     * Приватный метод для генерации случайного 3-цифрового CVV-кода.
     *
     * @return Строка из 3 цифр.
     */
    public String generateCVV() {
        int cvv = secureRandom.nextInt(CVV_MAX - CVV_MIN + 1) + CVV_MIN; // От 100 до 999
        return String.valueOf(cvv);
    }

    /**
     * Приватный метод для шифрования отдельного поля.
     *
     * @param plainText Исходный текст.
     * @return Зашифрованные данные в формате Base64 (IV + CipherText).
     * @throws Exception В случае ошибки шифрования.
     */
    private String encryptField(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        byte[] iv = new byte[IV_SIZE];
        secureRandom.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));

        // Объединяем IV и шифротекст
        byte[] ivAndCipherText = new byte[IV_SIZE + cipherText.length];
        System.arraycopy(iv, 0, ivAndCipherText, 0, IV_SIZE);
        System.arraycopy(cipherText, 0, ivAndCipherText, IV_SIZE, cipherText.length);

        // Кодируем в Base64
        return Base64.getEncoder().encodeToString(ivAndCipherText);
    }

    /**
     * Приватный метод для дешифрования отдельного поля.
     *
     * @param cipherData Зашифрованные данные в формате Base64 (IV + CipherText).
     * @return Дешифрованный текст.
     * @throws Exception В случае ошибки дешифрования.
     */
    private String decryptField(String cipherData) throws Exception {
        byte[] ivAndCipherText = Base64.getDecoder().decode(cipherData);
        if (ivAndCipherText.length < IV_SIZE) {
            throw new IllegalArgumentException("Зашифрованные данные слишком короткие.");
        }

        // Разделяем IV и шифротекст
        byte[] iv = new byte[IV_SIZE];
        byte[] cipherText = new byte[ivAndCipherText.length - IV_SIZE];
        System.arraycopy(ivAndCipherText, 0, iv, 0, IV_SIZE);
        System.arraycopy(ivAndCipherText, IV_SIZE, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] plainTextBytes = cipher.doFinal(cipherText);
        return new String(plainTextBytes, "UTF-8");
    }
}

