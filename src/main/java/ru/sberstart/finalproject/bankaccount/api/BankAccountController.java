package ru.sberstart.finalproject.bankaccount.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountActiveInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountCreationRequestDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountDeactivateInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountCreationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountInformationResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountStateRequestDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.response.BankAccountSuccessTransactionResponseDTO;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountTransactionRequestDTO;
import ru.sberstart.finalproject.bankaccount.application.service.BankAccountService;
import ru.sberstart.finalproject.global.GlobalAppExceptionDTO;

/**
 * Контроллер, обрабатывающий запросы по работе с сущностью банковских счетов.
 * Основные функциональные особенности: создание банковского счета; получение информации о банковском счете;
 * подтверждение банковского счета; приостановка действия банковского счета; закрытие банковского счета;
 * выполнение транзакций между банковскими счетами
 */
@RestController
@RequestMapping("/api/v1/bankaccounts")
public class BankAccountController {
    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    /**
     * Создание нового банковского счета.
     * **Доступные Роли:** USER, MANAGER
     *
     * @param creationDTO DTO с информацией для создания банковского счета.
     * @return ResponseEntity с полной информацией о созданном банковском счете.
     */
    @Operation(summary = "Создание нового банковского счета",
            description = "Метод создает новый банковский счет и возвращает DTO с полной информацией о созданном счете.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банковский счет успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAccountCreationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для создания счета",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "405", description = "Метод не поддерживается",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasAnyRole('USER', 'MANAGER')")
    @PostMapping("/new")
    public ResponseEntity<BankAccountCreationResponseDTO> createBankAccount(
            @Parameter(description = "DTO с информацией для создания банковского счета", required = true)
            @RequestBody BankAccountCreationRequestDTO creationDTO) {
        BankAccountCreationResponseDTO createdBankAccount = service.createBankAccount(creationDTO);
        return new ResponseEntity<>(createdBankAccount, HttpStatus.OK);
    }

    /**
     * Получение полной информации о банковском счете по его номеру.
     * **Доступные Роли:** USER, MANAGER
     *
     * @param number Номер банковского счета.
     * @return ResponseEntity с полной информацией о банковском счете.
     */
    @Operation(summary = "Получение полной информации о банковском счете по его номеру",
            description = "Метод выполняет JOIN таблиц BankAccounts, Banks и Users, возвращая DTO с полной информацией.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Возвращает полную информацию о счете",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAccountInformationResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Счет с указанным номером не найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasAnyRole('USER', 'MANAGER')")
    @GetMapping("/info/{number}")
    public ResponseEntity<BankAccountInformationResponseDTO> getBankAccountFullInfo(
            @Parameter(description = "Номер банковского счета", required = true)
            @PathVariable String number) {
        BankAccountInformationResponseDTO bankAccountFullInfo = service.getByNumber(number);
        return new ResponseEntity<>(bankAccountFullInfo, HttpStatus.OK);
    }

    /**
     * Подтверждение банковского счета.
     * **Доступные Роли:** MANAGER
     *
     * @param stateDTO DTO с информацией о состоянии банковского счета.
     * @return ResponseEntity с информацией об активированном банковском счете.
     */
    @Operation(summary = "Подтверждение банковского счета",
            description = "Метод подтверждает банковский счет и возвращает DTO с информацией об активированном счете.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банковский счет успешно подтвержден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAccountActiveInformationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для подтверждения счета",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "405", description = "Метод не поддерживается",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "422", description = "Метод не может быть выполнен для банковского счета в текущем состоянии",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/approve")
    public ResponseEntity<BankAccountActiveInformationResponseDTO> approveBankAccount(
            @Parameter(description = "DTO с информацией о состоянии банковского счета", required = true)
            @RequestBody BankAccountStateRequestDTO stateDTO) {
        BankAccountActiveInformationResponseDTO activatedBankAccount = service.approveBankAccount(stateDTO);
        return new ResponseEntity<>(activatedBankAccount, HttpStatus.OK);
    }

    /**
     * Приостановка действия банковского счета.
     * **Доступные Роли:** MANAGER, ADMIN
     *
     * @param stateDTO DTO с информацией о состоянии банковского счета.
     * @return ResponseEntity с информацией о приостановленном банковском счете.
     */
    @Operation(summary = "Приостановка действия банковского счета",
            description = "Метод приостанавливает действие банковского счета и возвращает DTO с информацией о приостановленном счете.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банковский счет успешно приостановлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAccountDeactivateInformationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для приостановки счета",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "405", description = "Метод не поддерживается",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "422", description = "Метод не может быть выполнен для банковского счета в текущем состоянии",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PatchMapping("/suspend")
    public ResponseEntity<BankAccountDeactivateInformationResponseDTO> suspendBankAccount(
            @Parameter(description = "DTO с информацией о состоянии банковского счета", required = true)
            @RequestBody BankAccountStateRequestDTO stateDTO) {
        BankAccountDeactivateInformationResponseDTO deactivatedBankAccount = service.suspendBankAccount(stateDTO);
        return new ResponseEntity<>(deactivatedBankAccount, HttpStatus.OK);
    }

    /**
     * Закрытие банковского счета.
     * **Доступные Роли:** ADMIN
     *
     * @param stateDTO DTO с информацией о состоянии банковского счета.
     * @return ResponseEntity с информацией о закрытом банковском счете.
     */
    @Operation(summary = "Закрытие банковского счета",
            description = "Метод закрывает банковский счет и возвращает DTO с информацией о закрытом счете.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банковский счет успешно закрыт",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAccountDeactivateInformationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для закрытия счета",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "405", description = "Метод не поддерживается",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "422", description = "Метод не может быть выполнен для банковского счета в текущем состоянии",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/close")
    public ResponseEntity<BankAccountDeactivateInformationResponseDTO> closeBankAccount(
            @Parameter(description = "DTO с информацией о состоянии банковского счета", required = true)
            @RequestBody BankAccountStateRequestDTO stateDTO) {
        BankAccountDeactivateInformationResponseDTO deactivatedBankAccount = service.closeBankAccount(stateDTO);
        return new ResponseEntity<>(deactivatedBankAccount, HttpStatus.OK);
    }


    /**
     * Выполнение транзакции между банковскими счетами.
     * **Доступные Роли:** USER, MANAGER
     *
     * @param transactionDTO DTO с информацией о транзакции.
     * @return ResponseEntity с информацией об успешной транзакции.
     */
    @Operation(summary = "Выполнение транзакции между банковскими счетами",
            description = "Метод выполняет транзакцию между банковскими счетами и возвращает DTO с информацией об успешной транзакции.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакция успешно выполнена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAccountSuccessTransactionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для выполнения транзакции",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "403", description = "Операция запрещена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/transaction/execute")
    public ResponseEntity<BankAccountSuccessTransactionResponseDTO> executeTransaction(
            @Parameter(description = "DTO с информацией о транзакции", required = true)
            @RequestBody BankAccountTransactionRequestDTO transactionDTO) {
        BankAccountSuccessTransactionResponseDTO successTransaction = service.executeTransaction(transactionDTO);
        return new ResponseEntity<>(successTransaction, HttpStatus.OK);
    }
}