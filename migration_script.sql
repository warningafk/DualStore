-- Script de migración para separar usuarios admin y clientes
-- Ejecutar este script en tu base de datos

-- 1. Crear tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    direccion VARCHAR(500),
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado TINYINT DEFAULT 1
);

-- 2. Migrar usuarios con rol 'cliente' a la nueva tabla clientes
INSERT INTO clientes (nombre_completo, correo, contrasena, direccion, fecha_registro, estado)
SELECT 
    u.nombre_completo,
    u.correo,
    u.contrasena,
    u.direccion,
    u.fecha_registro,
    u.estado
FROM usuario u
INNER JOIN rol r ON u.rol_id = r.id
WHERE LOWER(r.nombre) = 'cliente'
AND u.estado = 1;

-- 3. Eliminar usuarios con rol 'cliente' de la tabla usuario
-- (Opcional: puedes comentar esta línea si quieres mantener un respaldo)
DELETE u FROM usuario u
INNER JOIN rol r ON u.rol_id = r.id
WHERE LOWER(r.nombre) = 'cliente';

-- 4. Verificar que solo quedan usuarios admin en la tabla usuario
SELECT 
    u.nombre_completo,
    u.correo,
    r.nombre as rol
FROM usuario u
LEFT JOIN rol r ON u.rol_id = r.id
WHERE u.estado = 1;

-- 5. Verificar que los clientes están en la nueva tabla
SELECT 
    nombre_completo,
    correo,
    estado
FROM clientes
WHERE estado = 1;

-- 6. Crear algunos clientes de prueba (opcional)
INSERT INTO clientes (nombre_completo, correo, contrasena, direccion, telefono) VALUES
('Juan Pérez', 'juan.perez@example.com', '$2a$10$DummyHashForTesting', 'Av. Principal 123', '987654321'),
('María García', 'maria.garcia@example.com', '$2a$10$DummyHashForTesting', 'Jr. Lima 456', '912345678'),
('Carlos Mendoza', 'carlos.mendoza@example.com', '$2a$10$DummyHashForTesting', 'Calle Real 789', '923456789');

-- NOTA: Las contraseñas arriba son hashes de prueba. 
-- Para contraseñas reales, usa BCrypt desde la aplicación.

-- 7. Crear índices para mejorar rendimiento
CREATE INDEX idx_clientes_correo ON clientes(correo);
CREATE INDEX idx_clientes_estado ON clientes(estado);

COMMIT;
