package ru.practicum.shareit.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(schema = "public", name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @Email(message = "Email should be valid")
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @NotBlank(message = "name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;
}
