package ru.sberstart.finalproject.application.manager;

import ru.sberstart.finalproject.api.dto.card.inner.CardCustomizingDTO;
import ru.sberstart.finalproject.domain.enitity.card.Card;
import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;
import ru.sberstart.finalproject.domain.state.card.CardStateFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Класс для управления картами (Card) в рамках выполнения бизнес-логики.
 * Основные функции включают в себя: конфигурацию чувствительных данных карты (CardSecret) в процессе выпуска карты,
 * проверку и изменение состояния карт.
 */
public class CardManager {
    private static final Pattern PATTERN = Pattern.compile("^\\d{8}-[a-fA-F0-9]{32}$");

    /**
     * Создает новый банковский счет на основе предоставленных данных.
     *
     * @param cardCustomizingDTO DTO с данными для создания банковского счета.
     * @return Экземпляр созданного банковского счета.
     */
    public Card customizeCard(CardCustomizingDTO cardCustomizingDTO) {
        return new Card.Builder()
                .withBankAccountId(cardCustomizingDTO.getBankAccountId())
                .withNumber(cardCustomizingDTO.getCardNumber())
                .withState(CardStates.ORDERED)
                .build();
    }

    /**
     * Переводит заказ карты в состояние "Активный".
     *
     * @param card Экземпляр банковского счета, который необходимо активировать.
     */
    public void approveCard(Card card) {
        // сперва получается текущее состояние счета, на основании чего вызывается управляющий класс состояния
        // через фабрику, а затем пробуем кастануть изменение состояния, состояние контролирует возможность операции
        CardStateFactory.getState(card).approveCardIssue(card);
    }

    public void activateCard(Card card) {
        CardStateFactory.getState(card).activateCard(card);
    }

    /**
     * Переводит карту в состояние "Приостановленный".
     *
     * @param card Экземпляр банковского счета, который необходимо приостановить.
     */
    public void suspendCard(Card card) {
        CardStateFactory.getState(card).suspendCard(card);
    }

    /**
     * Переводит карту в состояние "Закрытый".
     *
     * @param card Экземпляр банковского счета, который необходимо закрыть.
     */
    public void blockExpireCard(Card card) {
        CardStateFactory.getState(card).blockExpireCard(card);
    }

    /*-------------------АЛГОРИТМ ГЕНЕРАЦИИ НОМЕРА КАРТЫ----------------------*/

    /**
     * Генерирует 16-значный номер карты на основе UUID банка.
     *
     * @param bankUUID UUID банка
     * @return 16-значный номер карты
     */
    public String generateCardNumber(UUID bankUUID) {
        String iin = getIINFromUUID(bankUUID); // Первые 6 цифр
        String accountNumber = generateRandomDigits(9); // Следующие 9 цифр
        String partialCardNumber = iin + accountNumber;
        String checkDigit = calculateLuhnCheckDigit(partialCardNumber);
        return partialCardNumber + checkDigit;
    }

    /**
     * Преобразует UUID банка в 6-значный IIN/BIN.
     *
     * @param uuid UUID банка
     * @return 6-значный IIN/BIN
     */
    private static String getIINFromUUID(UUID uuid) {
        try {
            // Используем MD5 для хеширования UUID
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(uuid.toString().getBytes());

            // Преобразуем хеш в положительное число
            BigInteger number = new BigInteger(1, hash);

            // Извлекаем последние 6 цифр
            String hashString = number.toString();
            if (hashString.length() < 6) {
                // Если хеш короче 6 цифр, дополняем нулями слева
                hashString = String.format("%06d", number);
            }
            return hashString.substring(hashString.length() - 6);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при хешировании UUID: " + e.getMessage());
        }
    }

    /**
     * Генерирует строку из случайных цифр заданной длины.
     *
     * @param length Длина строки
     * @return Строка из случайных цифр
     */
    private static String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // Добавляем цифру от 0 до 9
        }

        return sb.toString();
    }

    /**
     * Вычисляет контрольную цифру по алгоритму Луна.
     *
     * @param number Первые 15 цифр номера карты
     * @return Контрольная цифра
     */
    private static String calculateLuhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = true;

        // Проходимся по цифрам справа налево
        for(int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if(alternate) {
                n *= 2;
                if(n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        int mod = sum % 10;
        int checkDigit = (10 - mod) % 10;
        return String.valueOf(checkDigit);
    }
}