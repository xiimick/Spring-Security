package com.example.repository;

import com.example.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // Поиск пользователя по email
    User findByEmail(String email);
}
