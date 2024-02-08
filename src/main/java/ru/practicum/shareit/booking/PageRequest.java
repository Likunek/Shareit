package ru.practicum.shareit.booking;
import org.springframework.data.domain.Sort;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class PageRequest extends AbstractPageRequest {

    private final Sort sort;
    public PageRequest(int page, int size, Sort sort) {
        super(page, size);
        this.sort = sort;
    }

    @Override
    public int getNumberOfPages() {
        return 0;
    }

    @Override
    public PageFormat getPageFormat(int i) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public Printable getPrintable(int i) throws IndexOutOfBoundsException {
        return null;
    }
}
