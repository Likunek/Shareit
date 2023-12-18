package ru.practicum.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({ValidationExceptionUser.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validationError(final RuntimeException e) {
        return Map.of("error", "ошибка валидации",
                "errorMessage", e.getMessage());
    }

//    @ExceptionHandler({FilmNotFoundException.class, UserNotFoundException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public Map<String, String> objectNotFound(final RuntimeException e) {
//        return Map.of("error", "объект не найден",
//                "errorMessage", e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Map<String, String> getException(final Throwable e) {
//        return Map.of("error", "Произошла непредвиденная ошибка.",
//                "errorMessage", e.getMessage());
//    }
}