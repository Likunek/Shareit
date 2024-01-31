package ru.practicum.shareit.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    List<Item> findByUserId(long userId);

    @Query(value = "select * " +
            "from items as t " +
            "where (LOWER(t.name) LIKE CONCAT('%', ?1, '%') or LOWER(t.description) LIKE CONCAT('%', ?1, '%'))" +
            "and t.available = true",
            nativeQuery = true)
    List<ItemInfo> findItemsWhereContainsTheText(String text);

    void deleteById(Long itemId);
}
