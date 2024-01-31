package ru.practicum.shareit.exception;

public class TheItemHasAlreadyBeenBooked extends RuntimeException {
    public TheItemHasAlreadyBeenBooked(final String message) {
        super(message);
    }
}
