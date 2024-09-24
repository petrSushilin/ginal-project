package ru.sberstart.finalproject.bank.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberstart.finalproject.bank.api.dto.BankCreationDTO;
import ru.sberstart.finalproject.bank.api.dto.BankFullInformationDTO;
import ru.sberstart.finalproject.bank.api.dto.BankInformationDTO;
import ru.sberstart.finalproject.bank.application.service.BankService;

/**
 * Контроллер для управления банковскими операциями.
 */
@RequestMapping("/api/v1/banks")
@RestController
public class BankController {
    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    /**
     * Создает новый банк.
     *
     * @param bankCreationDTO DTO для создания банка.
     * @return Полная информация о созданном банке.
     */
    @Operation(summary = "Создание нового банка", description = "Создает новый банк на основе предоставленных данных.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Банк успешно создан", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankFullInformationDTO.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса", content = @Content),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
    })
    @PostMapping("/new")
    public ResponseEntity<BankFullInformationDTO> createBank(@RequestBody BankCreationDTO bankCreationDTO) {
        BankFullInformationDTO bankInformationDTO = service.create(bankCreationDTO);
        return new ResponseEntity<>(bankInformationDTO, HttpStatus.OK);
    }

    /**
     * Получает информацию о банке по идентификационному номеру.
     *
     * @param identityNumber Идентификационный номер банка.
     * @return Информация о банке.
     */
    @Operation(summary = "Получение информации о банке", description = "Возвращает информацию о банке по его идентификационному номеру.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Информация о банке успешно получена", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankInformationDTO.class))),
        @ApiResponse(responseCode = "404", description = "Банк не найден", content = @Content),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
    })
    @GetMapping("/{identityNumber}")
    public ResponseEntity<BankInformationDTO> getByIdentityNumber(@PathVariable String identityNumber) {
        BankInformationDTO bankInformationDTO = service.getByIdentityNumber(identityNumber);
        return new ResponseEntity<>(bankInformationDTO, HttpStatus.OK);
    }

    /**
     * Обновляет информацию о банке.
     *
     * @param bankInformationDTO DTO для обновления информации о банке.
     * @return Обновленная информация о банке.
     */
    @Operation(summary = "Обновление информации о банке", description = "Обновляет информацию о банке на основе предоставленных данных.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Информация о банке успешно обновлена", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankInformationDTO.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса", content = @Content),
        @ApiResponse(responseCode = "404", description = "Банк не найден", content = @Content),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<BankInformationDTO> updateBank(@RequestBody BankInformationDTO bankInformationDTO) {
        BankInformationDTO updatedBankInformationDTO = service.update(bankInformationDTO);
        return new ResponseEntity<>(updatedBankInformationDTO, HttpStatus.OK);
    }
}