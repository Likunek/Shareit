package ru.practicum.shareit.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ValidationExceptionUser extends RuntimeException {

    public ValidationExceptionUser(final String message) {
        super(message);
    }
}
