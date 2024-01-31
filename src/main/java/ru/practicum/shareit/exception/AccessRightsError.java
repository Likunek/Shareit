package ru.practicum.shareit.exception;

public class AccessRightsError extends RuntimeException {
    public AccessRightsError(final String message) {
        super(message);
    }
}
