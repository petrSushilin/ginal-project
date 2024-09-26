package ru.sberstart.finalproject.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sberstart.finalproject.global.exceptions.ForbiddenTransactionException;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;
import ru.sberstart.finalproject.global.exceptions.RecordNotFoundException;
import ru.sberstart.finalproject.global.exceptions.UnsuccessfulOperationException;

/**
 * Глобальный обработчик исключений для всего приложения.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработка исключения некорректного формата числа.
     *
     * @param ex исключение NumberFormatException
     * @return ResponseEntity с информацией об ошибке
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<GlobalAppExceptionDTO> handleNumberFormatException(NumberFormatException ex) {
        GlobalAppExceptionDTO exception = new GlobalAppExceptionDTO(HttpStatus.BAD_REQUEST.value(), "Некорректный формат числа: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    /**
     * Обработка исключения запрещённой транзакции.
     *
     * @param ex исключение ForbiddenTransactionException
     * @return ResponseEntity с информацией об ошибке
     */
    @ExceptionHandler(ForbiddenTransactionException.class)
    public ResponseEntity<GlobalAppExceptionDTO> handleForbiddenTransactionException(ForbiddenTransactionException ex) {
        GlobalAppExceptionDTO exception = new GlobalAppExceptionDTO(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception);
    }

    /**
     * Обработка исключения недопустимых действий в текущем состоянии.
     *
     * @param ex исключение NoValidateActionsException
     * @return ResponseEntity с информацией об ошибке
     */
    @ExceptionHandler(NotAvailableActionsException.class)
    public ResponseEntity<GlobalAppExceptionDTO> handleNotAvailableActionsException(NotAvailableActionsException ex) {
        GlobalAppExceptionDTO exception = new GlobalAppExceptionDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception);
    }

    /**
     * Обработка исключения, когда запись не найдена.
     *
     * @param ex исключение RecordNotFoundException
     * @return ResponseEntity с информацией об ошибке
     */
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<GlobalAppExceptionDTO> handleRecordNotFoundException(RecordNotFoundException ex) {
        GlobalAppExceptionDTO exception = new GlobalAppExceptionDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    /**
     * Обработка исключения неудачной операции.
     *
     * @param ex исключение UnsuccessfulOperationException
     * @return ResponseEntity с информацией об ошибке
     */
    @ExceptionHandler(UnsuccessfulOperationException.class)
    public ResponseEntity<GlobalAppExceptionDTO> handleUnsuccessfulOperationException(UnsuccessfulOperationException ex) {
        GlobalAppExceptionDTO exception = new GlobalAppExceptionDTO(HttpStatus.METHOD_NOT_ALLOWED.value(), "Не удалось выполнить операцию.");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exception);
    }

//    /**
//     * Обработка непредвиденных исключений времени выполнения.
//     *
//     * @param ex исключение RuntimeException
//     * @return ResponseEntity с информацией об ошибке
//     */
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<GlobalAppExceptionDTO> handleRuntimeException(RuntimeException ex) {
//        GlobalAppExceptionDTO exception = new GlobalAppExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Внутренняя ошибка сервера.");
//        // Логирование исключения для отладки
//        // logger.error("RuntimeException:", ex);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
//    }
//
//    /**
//     * Обработка всех остальных исключений.
//     *
//     * @param ex исключение Exception
//     * @return ResponseEntity с информацией об ошибке
//     */
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<GlobalAppExceptionDTO> handleException(Exception ex) {
//        GlobalAppExceptionDTO exception = new GlobalAppExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Произошла внутренняя ошибка сервера.");
//        // Логирование исключения для отладки
//        // logger.error("Exception:", ex);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
//    }
}