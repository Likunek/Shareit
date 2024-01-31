package ru.practicum.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({ValidationExceptionUser.class, TheItemHasAlreadyBeenBooked.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public Map<String, String> validationError(final RuntimeException e) {
        return Map.of("error", "ошибка валидации",
                "errorMessage", e.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class, ItemNotFoundException.class,
            BookingNotFoundException.class, AccessRightsError.class})
    @ResponseStatus(HttpStatus.NOT_FOUND) //404
    public Map<String, String> objectNotFound(final RuntimeException e) {
        return Map.of("error", "объект не найден",
                "errorMessage", e.getMessage());
    }

    @ExceptionHandler({ExceptionEnum.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public Map<String, String> getException(final RuntimeException e) {
        return Map.of("error", e.getMessage(),
                "errorMessage", e.getMessage());
    }
}