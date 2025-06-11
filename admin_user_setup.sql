-- Script SQL para crear un usuario administrador de prueba
-- Ejecutar en tu base de datos MySQL

-- 1. Crear el usuario admin con contraseña hasheada
-- Contraseña en texto plano: admin123
-- Hash BCrypt: $2a$10$N1EQ8vzEJQJLcRgKfHKDfurh6.eOVTh.4mKlJY5jZaAp1k1KnY7Ou

INSERT INTO usuario (nombre_completo, correo, contrasena, direccion, estado, rol_id) 
VALUES ('Administrador', 'admin@dualstore.com', '$2a$10$N1EQ8vzEJQJLcRgKfHKDfurh6.eOVTh.4mKlJY5jZaAp1k1KnY7Ou', 'Dirección Admin', 1, 1)
ON DUPLICATE KEY UPDATE 
    contrasena = '$2a$10$N1EQ8vzEJQJLcRgKfHKDfurh6.eOVTh.4mKlJY5jZaAp1k1KnY7Ou';

-- Verificar que existe el rol de administrador
INSERT INTO rol (nombre, estado) VALUES ('admin', 1) ON DUPLICATE KEY UPDATE estado = 1;
INSERT INTO rol (nombre, estado) VALUES ('ADMIN', 1) ON DUPLICATE KEY UPDATE estado = 1;

-- Verificar que los datos están correctos
SELECT u.id, u.nombre_completo, u.correo, r.nombre as rol 
FROM usuario u 
LEFT JOIN rol r ON u.rol_id = r.id 
WHERE u.correo = 'admin@dualstore.com';
