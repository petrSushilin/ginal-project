package ru.sberstart.finalproject.domain.state.card;

import ru.sberstart.finalproject.domain.enitity.card.Card;

/**
 * Данный поведенческий интерфейс банковского счета в разных состояниях является базовым.
 */
public interface CardState {
    /**
     * Метод выпуска карты для существующего банковского счета.
     * Доступен только в состоянии: ACTIVE.
     *
     * @param card
     */
    void issueCard(Card card);

    /**
     * Метод подтверждения выпуска карты.
     * Доступен только в состоянии: ORDERED, BLOCKED.
     *
     * @param card
     */
        void approveCardIssue(Card card);

    /**
     * Метод подтверждения выдачи и активации карты.
     * Доступен только в состоянии: APPROVED.
     *
     * @param card
     */
    void activateCard(Card card);

    /**
     * Метод приостановки банковского счета.
     * Доступен только в состоянии: ORDERED, BLOCKED.
     *
     * @param card
     */
    void suspendCard(Card card);

    /**
     * Метод закрытия банковского счета.
     * Доступен только в состоянии: ORDERED, ACTIVE.
     *
     * @param card
     */
    void blockExpireCard(Card card);
}
