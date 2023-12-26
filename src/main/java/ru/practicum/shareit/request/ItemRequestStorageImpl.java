package ru.practicum.shareit.request;

import ru.practicum.shareit.user.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ItemRequestStorageImpl implements  ItemRequestStorage {
    private final Map<Long, ItemRequest> storage = new HashMap<>();
    private long generatedId;
    @Override
    public ItemRequest create(ItemRequest itemRequest, User user) {
        itemRequest.setId(generatedId);
        itemRequest.setRequestors(user);
        itemRequest.setCreated(LocalDate.now());
        storage.put(itemRequest.getId(), itemRequest);
        return itemRequest;
    }
}
