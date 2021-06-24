package uz.pdp.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "Email kiritilmadi!!!")
    @NotNull(message = "Emmail yozilmadi")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "parol kiritilmadi!!!")
    @Column(nullable = false)
    private String password;
}
