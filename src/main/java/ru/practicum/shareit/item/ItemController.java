package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.ItemNotFoundException;
import ru.practicum.shareit.exception.UserNotFoundException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemController {
    ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public ItemDto get(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long itemId) {
        return itemService.getItem(itemId, userId);
    }

    @PostMapping
    public ItemDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                          @Valid @RequestBody ItemDto item) {
        if (userId !=null) {
            return itemService.addNewItem(userId, item);
        }
        throw new UserNotFoundException("User not found");
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long itemId,
                          @Valid @RequestBody ItemDtoUpdate item) {
        if (userId !=null) {
            return itemService.updateItem(itemId, userId, item);
        }
        throw new UserNotFoundException("User not found");
    }

    @GetMapping
    public List<ItemDto> getAll(@RequestHeader("X-Sharer-User-Id") Long userId) {
        if (userId !=null) {
            return itemService.getUserItems(userId);
        }
        throw new UserNotFoundException("User not found");
    }

    @GetMapping("/search")
    public List<ItemInfo> getItemByQueryField(@RequestParam(name = "text") String queryField) {
        return itemService.search(queryField.toLowerCase());
    }
    @PostMapping("/{itemId}/comment")
    public Comment getComment(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long itemId, @Valid @RequestBody Comment comment) {
        if (userId !=null) {
            if (itemId != null) {
                return itemService.addComment(userId, itemId, comment);
            }
           throw new ItemNotFoundException("Item not found");
        }
        throw new UserNotFoundException("User not found");
    }


}
