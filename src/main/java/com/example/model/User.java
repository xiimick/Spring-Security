package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)  // Используйте LAZY загрузку
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    // Конструктор без параметров (необходим для JPA)
    public User() {}

    // Конструктор для удобства создания
    public User(String name, String email, String password, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
