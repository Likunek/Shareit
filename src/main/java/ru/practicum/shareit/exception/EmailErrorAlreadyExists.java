package ru.practicum.shareit.exception;

public class EmailErrorAlreadyExists extends RuntimeException {
    public EmailErrorAlreadyExists(final String message) {
        super(message);
    }
}
