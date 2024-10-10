package ru.sberstart.finalproject.application.service;

import ru.sberstart.finalproject.api.dto.card.inner.CardCustomizingDTO;
import ru.sberstart.finalproject.api.dto.card.inner.CardGeneralInformationDTO;
import ru.sberstart.finalproject.api.dto.card.inner.CardSecretInformationDTO;
import ru.sberstart.finalproject.api.dto.card.request.CardIssueRequestDTO;
import ru.sberstart.finalproject.api.dto.card.request.CardStateRequestDTO;
import ru.sberstart.finalproject.api.dto.card.response.CardStateResponseDTO;
import ru.sberstart.finalproject.application.manager.CardManager;
import ru.sberstart.finalproject.domain.enitity.card.Card;
import ru.sberstart.finalproject.domain.enitity.card.CardSecret;
import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;
import ru.sberstart.finalproject.infrastructure.repostitory.CardRepositoryImpl;
import ru.sberstart.finalproject.mapper.CardMapper;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;

import java.util.List;
import java.util.UUID;

public class CardService {
    private final CardSecretService cardSecretService;
    private final CardManager manager;
    private final CardRepositoryImpl repository;

    public CardService(CardSecretService cardSecretService, CardManager manager, CardRepositoryImpl repository) {
        this.cardSecretService = cardSecretService;
        this.manager = manager;
        this.repository = repository;
    }

    public CardStateResponseDTO issueCard(CardIssueRequestDTO cardIssueDTO) {
        UUID bankId = UUID.fromString(cardIssueDTO.bankId());
        String cardNumber = manager.generateCardNumber(bankId);

        String bankAccountNumber = cardIssueDTO.bankAccountNumber();
        CardCustomizingDTO cardCustomizingDTO = cardSecretService.register(bankAccountNumber, cardNumber);

        Card card = manager.customizeCard(cardCustomizingDTO);
        Card createdCard = repository.create(card).orElseThrow(UnsuccessfulOperationException::new);

        return CardMapper.INSTANCE.toCardStateResponseDTO(createdCard);
    }

    public CardGeneralInformationDTO getGeneralInformationByNumber(String cardNumber) {
        return repository.getByNumber(cardNumber).orElseThrow(UnsuccessfulOperationException::new);
    }

    public List<CardGeneralInformationDTO> getListCardsByUserPhoneNumber(String userPhoneNumber) {
        List<CardGeneralInformationDTO> cardsList = repository.getCardsByUser(userPhoneNumber);
        if (cardsList.isEmpty()) throw new UnsuccessfulOperationException();
        return cardsList;
    }

    public CardSecretInformationDTO getSecretInformation(String cardNumber) {
        CardSecret cardSecret = cardSecretService.getCardSecret(cardNumber);
        return CardMapper.INSTANCE.toCardSecretInformation(cardSecret);
    }

    /**
     * Процесс подтверждения выпуска карты.
     *
     * @param stateDTO DTO с информацией с ожидаемым состоянием карты.
     * @return CardStateResponseDTO с информацией об подтверждении выпуска карты.
     */
    public CardStateResponseDTO approveCard(CardStateRequestDTO stateDTO) {
        if (!stateDTO.state().equals(CardStates.APPROVED)) throw new NotAvailableActionsException();
        return updateStateCard(stateDTO);
    }

    /**
     * Процесс активации карты.
     *
     * @param stateDTO DTO с информацией с ожидаемым состоянием карты.
     * @return CardStateResponseDTO с информацией об подтверждении выдачи и активации карты.
     */
    public CardStateResponseDTO activateCard(CardStateRequestDTO stateDTO) {
        if (!stateDTO.state().equals(CardStates.ACTIVE))
            throw new NotAvailableActionsException();
        return updateStateCard(stateDTO);
    }

    /**
     * Процесс приостановки действия карты.
     *
     * @param stateDTO DTO с информацией с ожидаемым состоянием карты.
     * @return CardStateResponseDTO с информацией о приостановке карты.
     */
    public CardStateResponseDTO suspendCard(CardStateRequestDTO stateDTO) {
        if (!stateDTO.state().equals(CardStates.BLOCKED)) throw new NotAvailableActionsException();
        return updateStateCard(stateDTO);
    }

    /**
     * Процесс блокирования карты.
     *
     * @param stateDTO DTO с информацией с ожидаемым состоянием карты.
     * @return CardStateResponseDTO с информацией о блокировке карты.
     */
    public CardStateResponseDTO blockExpireCard(CardStateRequestDTO stateDTO) {
        if (!stateDTO.state().equals(CardStates.EXPIRED)) throw new NotAvailableActionsException();
        return updateStateCard(stateDTO);
    }

    /**
     * Процесс обновления состояния карты.
     * Сначала выполняется изменение состояния карты, затем выполняется обновление записи в БД.
     * После корректного изменения состояния карты в БД, происходит отправка ожидаемого DTO сущности.
     *
     * @param stateDTO DTO с информацией с ожидаемым состоянием карты.
     * @return CardStateResponseDTO с информацией об обновленном состоянии счета.
     */
    private CardStateResponseDTO updateStateCard(CardStateRequestDTO stateDTO) {
        Card card = consistencyCardRecordWithState(stateDTO);

        Card cardRecord = repository.updateState(card).orElseThrow(UnsuccessfulOperationException::new);

        return CardMapper.INSTANCE.toCardStateResponseDTO(cardRecord);
    }

    /**
     * Внутренний метод, реализующий изменение состояния банковского счета.
     * В зависимости от переданного состояния, выполняется соответствующее действие.
     *
     * @param stateDTO DTO с информацией с ожидаемым состоянием карты.
     * @return BankAccount с обновленным состоянием.
     */
    private Card consistencyCardRecordWithState(CardStateRequestDTO stateDTO) {
        Card card = repository.getServiceInfoByNumber(stateDTO.number()).orElseThrow(UnsuccessfulOperationException::new);
        // для желаемого состояния вызываем соответствующий метод у менеджера
        switch (stateDTO.state()) {
            case ORDERED -> throw new UnsupportedOperationException();
            case APPROVED -> manager.approveCard(card);
            case ACTIVE -> manager.activateCard(card);
            case BLOCKED -> manager.suspendCard(card);
            case EXPIRED -> manager.blockExpireCard(card);
        }
        return card;
    }
}
