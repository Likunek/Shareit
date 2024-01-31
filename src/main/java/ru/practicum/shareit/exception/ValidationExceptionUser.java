package ru.practicum.shareit.exception;


public class ValidationExceptionUser extends RuntimeException {

    public ValidationExceptionUser(final String message) {
        super(message);
    }
}
