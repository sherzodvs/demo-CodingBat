package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(@Email(message = "Email kiritilmadi!!!") @NotNull(message = "Emmail yozilmadi") String email);
    boolean existsByEmailAndIdNot(@Email(message = "Email kiritilmadi!!!") @NotNull(message = "Emmail yozilmadi") String email, Integer id);
}
