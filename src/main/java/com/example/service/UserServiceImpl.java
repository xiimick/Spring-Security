package com.example.service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl extends UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        // Шифруємо пароль перед збереженням
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Створюємо базову роль (наприклад, "ROLE_USER")
        Role role = new Role();
        role.setName("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        // Призначаємо роль користувачу
        user.setRoles(roles);

        // Зберігаємо користувача в репозиторії
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}