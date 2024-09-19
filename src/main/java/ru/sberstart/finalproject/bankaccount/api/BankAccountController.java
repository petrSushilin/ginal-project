package ru.sberstart.finalproject.bankaccount.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberstart.finalproject.bankaccount.application.service.BankAccountService;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountActiveInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountCreationDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountDeactivateInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountFullInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountStateDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountSuccessTransactionDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountTransactionDTO;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;

/**
 * Контроллер, обрабатывающий запросы по работе с сущностью банковских счетов.
 */
@RequestMapping("/api/bankaccounts")
public class BankAccountController {
    private BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    /**
     * Маппинг обработки запросов создания банковского счета.
     *
     * @param creationDTO
     */
    @PostMapping("/new")
    public ResponseEntity<?> createBankAccount(@RequestBody BankAccountCreationDTO creationDTO) {
        BankAccountFullInformationResponseDTO createdBankAccount = service.createBankAccount(creationDTO);
        return new ResponseEntity<>(createdBankAccount, HttpStatus.OK);
    }

    /**
     * Маппинг обработки запросов подтверждения создания банковского счета.
     *
     * @param stateDTO
     */
    @PatchMapping("/approve")
    public ResponseEntity<?> approveBankAccount(@RequestBody BankAccountStateDTO stateDTO) {
        BankAccountActiveInformationResponseDTO activatedBankAccount = service.approveBankAccount(stateDTO);
        return new ResponseEntity<>(activatedBankAccount, HttpStatus.OK);
    }

    /**
     * Маппинг обработки запросов приостановки действия банковского счета.
     *
     * @param stateDTO
     */
    @PatchMapping("/suspend")
    public ResponseEntity<?> suspendBankAccount(@RequestBody BankAccountStateDTO stateDTO) {
        BankAccountDeactivateInformationResponseDTO deactivatedBankAccount = service.suspendBankAccount(stateDTO);
        return new ResponseEntity<>(deactivatedBankAccount, HttpStatus.OK);
    }

    /**
     * Маппинг обработки запросов закрытия банковского счета.
     *
     * @param stateDTO
     */
    // TODO: сделать хэндлер для обработки ошибок
    @PatchMapping("/close")
    public ResponseEntity<?> closeBankAccount(@RequestBody BankAccountStateDTO stateDTO) throws UnsuccessfulOperationException {
        BankAccountDeactivateInformationResponseDTO deactivatedBankAccount = service.closeBankAccount(stateDTO);
        return new ResponseEntity<>(deactivatedBankAccount, HttpStatus.OK);
    }


    @PatchMapping("/transaction")
    public ResponseEntity<?> transaction(@RequestBody BankAccountTransactionDTO transactionDTO) {
        BankAccountSuccessTransactionDTO successTransaction = service.transaction(transactionDTO);
        return new ResponseEntity<>(successTransaction, HttpStatus.OK);
    }
}
