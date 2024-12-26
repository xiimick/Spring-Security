package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Метод для сохранения пользователя
    public void saveUser(User user) {
        // Хешируем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Метод для получения всех пользователей
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Метод для проверки логина
    public boolean validateLogin(String email, String password) {
        User user = userRepository.findByEmail(email); // Ищем пользователя по email
        // Проверяем пароль, используя BCryptPasswordEncoder
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    // Метод для поиска пользователя по email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email); // Ищем пользователя по email
    }
}

