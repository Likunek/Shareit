package ru.practicum.shareit.item;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.request.ItemRequest;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(schema = "public", name = "items")
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @NotBlank(message = "login cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull
    @Column(name = "available", nullable = false)
    private Boolean available;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Comment> comments;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "request_id", nullable = false)
//    private ItemRequest request;
}
