package ru.sberstart.finalproject.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberstart.finalproject.global.GlobalAppExceptionDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserActiveResponseDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserCreationRequestDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserCreationResponseDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserDeactivateResponseDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserRolesRequestDTO;
import ru.sberstart.finalproject.api.dto.user.response.UserRolesResponseDTO;
import ru.sberstart.finalproject.api.dto.user.request.UserStatusRequestDTO;
import ru.sberstart.finalproject.application.service.UserService;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Создание нового пользователя.
     * Этот метод доступен только пользователям с ролью 'user'.
     *
     * @param userCreationDTO объект передачи данных для создания пользователя
     * @return сущность ответа, содержащая объект передачи данных ответа на создание пользователя
     */
    @Operation(summary = "Создание нового пользователя",
               description = "Этот метод доступен только пользователям с ролью 'user'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = UserCreationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ресурс не найден",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<UserCreationResponseDTO> createUser(@RequestBody UserCreationRequestDTO userCreationDTO) {
        UserCreationResponseDTO response = userService.createUser(userCreationDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Подтверждение пользователя.
     * Этот метод доступен только пользователям с ролью 'manager'.
     *
     * @param userStatusDTO объект передачи данных статуса пользователя
     * @return сущность ответа, содержащая объект передачи данных ответа на активацию пользователя
     */
    @Operation(summary = "Подтверждение пользователя",
               description = "Этот метод доступен только пользователям с ролью 'manager'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно подтвержден",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = UserActiveResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ресурс не найден",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/approve")
    public ResponseEntity<UserActiveResponseDTO> approveUser(@RequestBody UserStatusRequestDTO userStatusDTO) {
        UserActiveResponseDTO response = userService.approveUser(userStatusDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Блокировка пользователя.
     * Этот метод доступен только пользователям с ролью 'manager'.
     *
     * @param userStatusDTO объект передачи данных статуса пользователя
     * @return сущность ответа, содержащая объект передачи данных ответа на деактивацию пользователя
     */
    @Operation(summary = "Блокировка пользователя",
               description = "Этот метод доступен только пользователям с ролью 'manager'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно заблокирован",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = UserDeactivateResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ресурс не найден",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/block")
    public ResponseEntity<UserDeactivateResponseDTO> blockUser(@RequestBody UserStatusRequestDTO userStatusDTO) {
        UserDeactivateResponseDTO response = userService.blockUser(userStatusDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Изменение роли пользователя.
     * Этот метод доступен только пользователям с ролью 'admin'.
     *
     * @param userRolesDTO объект передачи данных ролей пользователя
     * @return сущность ответа, содержащая объект передачи данных ответа на изменение ролей пользователя
     */
    @Operation(summary = "Изменение роли пользователя",
               description = "Этот метод доступен только пользователям с ролью 'admin'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль пользователя успешно изменена",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = UserRolesResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ресурс не найден",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GlobalAppExceptionDTO.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/roles")
    public ResponseEntity<UserRolesResponseDTO> changeUserRole(@RequestBody UserRolesRequestDTO userRolesDTO) {
        UserRolesResponseDTO response = userService.changeUserRole(userRolesDTO);
        return ResponseEntity.ok(response);
    }
}
