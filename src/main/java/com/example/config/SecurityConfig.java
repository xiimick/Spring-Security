package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Разрешаем доступ к страницам регистрации и логина без аутентификации
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/users/register", "/users/login", "/users/logout").permitAll()  // Путь для публичных страниц
                        .anyRequest().authenticated()  // Для всех остальных запросов требуем аутентификацию
                )
                // Конфигурируем форму логина
                .formLogin(form -> form
                        .loginPage("/users/login")  // Указываем страницу логина
                        .permitAll()  // Разрешаем доступ всем
                )
                // Настройки выхода
                .logout(logout -> logout
                        .permitAll()  // Разрешаем всем выходить
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
