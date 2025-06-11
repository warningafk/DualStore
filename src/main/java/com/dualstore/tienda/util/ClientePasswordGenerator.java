package com.dualstore.tienda.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class para generar hashes BCrypt para clientes de prueba
 * Ejecutar este main para generar contraseñas encriptadas
 */
public class ClientePasswordGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Generar hashes para contraseñas de prueba
        String[] passwords = {"123456", "password", "cliente123", "admin123"};
        
        System.out.println("=== HASHES BCRYPT PARA CLIENTES DE PRUEBA ===");
        for (String password : passwords) {
            String hash = encoder.encode(password);
            System.out.println("Contraseña: " + password + " -> Hash: " + hash);
        }
        
        // Generar hashes específicos para insertar en base de datos
        System.out.println("\n=== INSERTS SQL CON HASHES REALES ===");
        System.out.println("-- Contraseña: 123456");
        System.out.println("INSERT INTO clientes (nombre_completo, correo, contrasena, direccion, telefono) VALUES");
        System.out.println("('Juan Pérez Cliente', 'juan.cliente@example.com', '" + encoder.encode("123456") + "', 'Av. Principal 123', '987654321');");
        
        System.out.println("\n-- Contraseña: password");
        System.out.println("INSERT INTO clientes (nombre_completo, correo, contrasena, direccion, telefono) VALUES");
        System.out.println("('María García Cliente', 'maria.cliente@example.com', '" + encoder.encode("password") + "', 'Jr. Lima 456', '912345678');");
        
        System.out.println("\n-- Contraseña: cliente123");
        System.out.println("INSERT INTO clientes (nombre_completo, correo, contrasena, direccion, telefono) VALUES");
        System.out.println("('Carlos Mendoza Cliente', 'carlos.cliente@example.com', '" + encoder.encode("cliente123") + "', 'Calle Real 789', '923456789');");
    }
}
