package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Страница регистрации
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration"; // Страница регистрации
    }

    // Обработка данных формы регистрации
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Здесь стоит хешировать пароль перед сохранением
        userService.saveUser(user); // Сохраняем нового пользователя в БД
        return "redirect:/users/login"; // Перенаправляем на страницу логина
    }

    // Страница логина
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Страница логина
    }

    // Обработка логина
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        // Проверяем валидность логина и пароля
        if (userService.validateLogin(email, password)) {
            return "redirect:/users/list"; // Если логин успешен, перенаправляем на страницу с пользователями
        } else {
            // Если логин не успешен, добавляем ошибку в модель
            model.addAttribute("errorMessage", "Invalid email or password!");
            return "login"; // Возвращаем на страницу логина с ошибкой
        }
    }

    // Страница с пользователями
    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers()); // Загружаем всех пользователей
        return "users"; // Страница с пользователями
    }
}
