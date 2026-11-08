-- MasterCel Pricing: esquema de la base de datos (MySQL 8)
-- Los porcentajes de configuracion_precios son solo de ejemplo.

CREATE DATABASE IF NOT EXISTS `mastercel-pricing`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE `mastercel-pricing`;

CREATE TABLE proveedores (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nombre VARCHAR(100) NOT NULL UNIQUE,
                             ubicacion VARCHAR(150) NOT NULL,
                             estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE consultas (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           tipo ENUM('PANTALLA','REPUESTO') NOT NULL,
                           marca VARCHAR(50) NOT NULL,
                           modelo VARCHAR(100) NOT NULL,
                           nombre_repuesto VARCHAR(100) NULL,
                           calidad ENUM('ORIGINAL','OLED','GENERICA') NOT NULL,
                           proveedor_id BIGINT NOT NULL,
                           costo DECIMAL(10,2) NOT NULL,
                           precio_cliente_garantia DECIMAL(10,2) NOT NULL,
                           precio_cliente_sin_garantia DECIMAL(10,2) NOT NULL,
                           precio_mayorista_garantia DECIMAL(10,2) NOT NULL,
                           precio_mayorista_sin_garantia DECIMAL(10,2) NOT NULL,
                           fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           estado BOOLEAN NOT NULL DEFAULT TRUE,
                           CONSTRAINT fk_consultas_proveedor
                               FOREIGN KEY (proveedor_id) REFERENCES proveedores(id)
);

CREATE TABLE configuracion_precios (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       tipo ENUM('PANTALLA','REPUESTO') NOT NULL,
                                       costo_desde DECIMAL(10,2) NOT NULL,
                                       costo_hasta DECIMAL(10,2) NULL,
                                       porcentaje_cliente_garantia DECIMAL(6,2) NOT NULL,
                                       porcentaje_cliente_sin_garantia DECIMAL(6,2) NOT NULL,
                                       porcentaje_mayorista_garantia DECIMAL(6,2) NOT NULL,
                                       porcentaje_mayorista_sin_garantia DECIMAL(6,2) NOT NULL,
                                       UNIQUE KEY uk_tipo_costo_desde (tipo, costo_desde)
);

-- Tramos de ejemplo (costo_desde incluido, costo_hasta excluido)
INSERT INTO configuracion_precios
(tipo, costo_desde, costo_hasta,
 porcentaje_cliente_garantia, porcentaje_cliente_sin_garantia,
 porcentaje_mayorista_garantia, porcentaje_mayorista_sin_garantia)
VALUES
    ('PANTALLA',    0.00,  200.00, 150.00, 120.00, 100.00, 70.00),
    ('PANTALLA',  200.00,  500.00, 100.00,  80.00,  70.00, 50.00),
    ('PANTALLA',  500.00, 1000.00,  70.00,  55.00,  45.00, 35.00),
    ('PANTALLA', 1000.00, 2000.00,  50.00,  40.00,  30.00, 25.00),
    ('PANTALLA', 2000.00,    NULL,  35.00,  25.00,  20.00, 15.00);