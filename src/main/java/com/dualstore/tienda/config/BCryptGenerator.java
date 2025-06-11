package com.dualstore.tienda.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "bill123"; // Cambia aquí la contraseña que quieras hashear
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Hash BCrypt: " + encodedPassword);
    }
}
