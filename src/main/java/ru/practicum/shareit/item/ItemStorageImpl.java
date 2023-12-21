package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.ItemNotFoundException;
import ru.practicum.shareit.exception.UserNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ItemStorageImpl implements ItemStorage {

    private final Map<Long, List<Item>> items = new HashMap<>();
    private long generatedId;

    @Override
    public ItemDto get(Long itemId) {
       for ( List<Item> item : items.values()) {
         List<Item> itemList = item.stream().filter(x -> x.getId().equals(itemId)).collect(Collectors.toList());
           if (itemList.size() > 0){
               log.info("Get item {}", itemList.get(0));
               return itemList.stream().map(ItemMapper :: toItemDto).collect(Collectors.toList()).get(0);
           }
       } throw new ItemNotFoundException("Вещь не найдена");
    }

    @Override
    public ItemDto add(ItemDto itemDto, Long userId) {
        itemDto.setId(++generatedId);
        Item item = ItemMapper.toItem(itemDto, userId);
        items.compute(userId, (ownerId, userItems) -> {
            if(userItems == null) {
                userItems = new ArrayList<>();
            }
            userItems.add(item);
            log.info("Add item {}", item);
            return userItems;
        });
        return itemDto;
    }

    @Override
    public ItemDtoUpdate update(ItemDtoUpdate itemDto, Long userId, Long itemId) {
        if(items.containsKey(userId)) {
            Item item = items.get(userId)
                    .stream().filter(x -> x.getId().equals(itemId)).collect(Collectors.toList()).get(0);
            if (item == null){
                throw new UserNotFoundException("Вы не являетесь владельцем данной вещи");
            }
            items.get(userId).removeIf(x -> x.getId().equals(itemId));
            if (itemDto.getName() != null){
                item.setName(itemDto.getName());
            }
            if (itemDto.getAvailable() != null){
                item.setAvailable(itemDto.getAvailable());
            }
            if (itemDto.getDescription() != null){
                item.setDescription(itemDto.getDescription());
            }
            if (itemDto.getRequest() != null){
                item.setRequest(itemDto.getRequest());
            }
            items.get(userId).add(item);
            log.info("Update item {}", item);
            return ItemMapper.toItemDtoUpdate(item);
        }
       throw new UserNotFoundException("Пользователь не найден");
    }

    @Override
    public List<ItemDto> getAll(Long userId) {
        return items.getOrDefault(userId, Collections.emptyList())
                .stream().map(ItemMapper :: toItemDto).collect(Collectors.toList());
    }



    @Override
    public void delete(Long itemId, Long userId) {
        if(items.containsKey(userId)) {
           Boolean delete = items.get(userId).removeIf(item -> item.getId().equals(itemId));
           if (delete.equals(false)) {
               throw new UserNotFoundException("Вы не являетесь владельцем данной вещи");
           }
        } else {
           throw new UserNotFoundException("Вы не являетесь владельцем какой-либо вещи");
        }
        log.info("Delete item id {}", itemId);
    }
    @Override
        public List<ItemDto> search(String text) {
        List<Item> itemsFilter;
        List<Item> itemsSearch = new ArrayList<>();
        if (!text.isBlank()) {
            for (List<Item> item : items.values()) {
                itemsFilter = item.stream()
                        .filter(x -> (x.getName().toLowerCase().contains(text) ||
                        x.getDescription().toLowerCase().contains(text)))
                        .filter(x -> x.getAvailable().equals(true)).collect(Collectors.toList());
                if (itemsFilter.size() != 0) {
                    for (Item itemFilter : itemsFilter) {
                        itemsSearch.add(itemFilter);
                    }
                }
            }
        }
            log.info("Search items {}", itemsSearch);
            return itemsSearch.stream().map(ItemMapper::toItemDto).collect(Collectors.toList());
    }


}
