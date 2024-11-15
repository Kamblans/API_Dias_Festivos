-- Verificar si la tabla ya existe y eliminarla si es necesario
DROP TABLE IF EXISTS festivo;

-- Crear la tabla festivo
CREATE TABLE festivo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dia INT,
    mes INT,
    nombre VARCHAR(255),
    tipo INT,
    dias_pascua INT
);

-- Insertar datos en la tabla festivo
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (1, 1, 'Año nuevo', 1, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (6, 1, 'Santos Reyes', 2, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (19, 3, 'San José', 2, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (0, 0, 'Jueves Santo', 3, -3);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (0, 0, 'Viernes Santo', 3, -2);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (0, 0, 'Domingo de Pascua', 3, 0);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (1, 5, 'Día del Trabajo', 1, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (0, 0, 'Ascensión del Señor', 4, 40);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (0, 0, 'Corpus Christi', 4, 61);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (0, 0, 'Sagrado Corazón de Jesús', 4, 68);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (29, 6, 'San Pedro y San Pablo', 2, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (20, 7, 'Independencia Colombia', 1, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (7, 8, 'Batalla de Boyacá', 1, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (15, 8, 'Asunción de la Virgen', 2, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (12, 10, 'Día de la Raza', 2, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (1, 11, 'Todos los santos', 2, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (11, 11, 'Independencia de Cartagena', 2, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (8, 12, 'Inmaculada Concepción', 1, null);
INSERT INTO festivo (dia, mes, nombre, tipo, dias_pascua) VALUES (25, 12, 'Navidad', 1, null);