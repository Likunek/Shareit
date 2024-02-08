package ru.practicum.shareit.booking;

import lombok.Data;

import java.awt.print.Pageable;
@Data
public abstract class AbstractPageRequest implements Pageable {

    private final int page;
    private final int size;

    public AbstractPageRequest(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("page index must not be less than zero");
        } else if (size < 1) {
            throw new IllegalArgumentException("page index must not be less than zero");
        } else {
            this.page = page;
            this.size = size;
        }
    }
}
