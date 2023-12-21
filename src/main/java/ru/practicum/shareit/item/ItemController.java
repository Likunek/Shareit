package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ItemDto get(@PathVariable Long itemId) {
        return itemService.get(itemId);
    }

    @PostMapping
    public ItemDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                          @Valid @RequestBody ItemDto item) {
        return itemService.add(item, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDtoUpdate update(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long itemId, @Valid @RequestBody ItemDtoUpdate item) {
        return itemService.update(item, userId, itemId);
    }

    @GetMapping
    public List<ItemDto> getAll(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getAll(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> getItemByQueryField(@RequestParam(name = "text") String queryField) {
        return itemService.search(queryField.toLowerCase());
    }
}
