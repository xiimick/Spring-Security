package com.example.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private PasswordEncoderUtil() {
        // Utility class
    }

    public static PasswordEncoder getPasswordEncoder() {
        return ENCODER;
    }

    public static String encode(String rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}