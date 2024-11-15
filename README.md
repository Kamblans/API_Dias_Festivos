# Festivos Colombia API

Este proyecto es una API para verificar si una fecha específica es un día festivo en Colombia. La API utiliza Spring Boot y una base de datos en memoria H2 para almacenar los datos de los festivos.


## Configuración del Proyecto

```java
1. Configurar la base de datos:
Asegúrate de que el archivo src/main/resources/application.properties esté configurado correctamente:

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
server.port=54373

2. Agregar los datos iniciales:
Coloca el archivo data.sql en la carpeta src/main/resources. Este archivo se ejecutará automáticamente al iniciar la aplicación y poblará la base de datos con los datos de los festivos.

DROP TABLE IF EXISTS festivo;

CREATE TABLE festivo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dia INT,
    mes INT,
    nombre VARCHAR(255),
    tipo INT,
    dias_pascua INT
);

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

Ejecución del Proyecto
3. Ejecutar la aplicación:
Abre tu IDE y ejecuta la clase principal FestivoApplication.java.
Alternativamente, puedes usar la línea de comandos para ejecutar el proyecto con Maven:

mvn spring-boot:run

4. Verificar la consola H2:
Accede a http://localhost:54373/h2-console.
Usa la URL jdbc:h2:mem:testdb, el usuario sa y la contraseña password.
Verifica que la tabla FESTIVO se haya creado correctamente y que los datos se hayan insertado.

Pruebas
Archivo de Consultas HTTP (requests.http)
### Prueba de fecha festiva fija
GET http://localhost:54373/api/festivos?TextoFecha=01/01/2019
### Resultado esperado: true

### Prueba de fecha festiva por Ley de Puente Festivo
GET http://localhost:54373/api/festivos?TextoFecha=07/01/2019
### Resultado esperado: true (Santos Reyes trasladado al lunes)

### Prueba de fecha festiva basada en Pascua
GET http://localhost:54373/api/festivos?TextoFecha=19/04/2019
### Resultado esperado: true (Viernes Santo)

### Prueba de fecha festiva basada en Pascua y Ley de Puente Festivo
GET http://localhost:54373/api/festivos?TextoFecha=03/06/2019
### Resultado esperado: true (Ascensión del Señor trasladado al lunes)

### Prueba de fecha no festiva
GET http://localhost:54373/api/festivos?TextoFecha=12/06/2019
### Resultado esperado: false

### Prueba de fecha no válida (30 de febrero)
GET http://localhost:54373/api/festivos?TextoFecha=30/02/2019
### Resultado esperado: {"Message": "Fecha no válida"}

### Prueba de fecha no válida (31 de abril)
GET http://localhost:54373/api/festivos?TextoFecha=31/04/2019
### Resultado esperado: {"Message": "Fecha no válida"}

### Prueba de fecha festiva fija (Navidad)
GET http://localhost:54373/api/festivos?TextoFecha=25/12/2019
### Resultado esperado: true

### Prueba de fecha festiva fija (Día del Trabajo)
GET http://localhost:54373/api/festivos?TextoFecha=01/05/2019
### Resultado esperado: true

### Prueba de fecha festiva por Ley de Puente Festivo (Día de la Raza)
GET http://localhost:54373/api/festivos?TextoFecha=14/10/2019
### Resultado esperado: true (Día de la Raza trasladado al lunes)

Uso de la API
Verificar si una fecha es festiva
Para verificar si una fecha es festiva, realiza una solicitud GET a la siguiente URL:

http://localhost:54373/api/festivos?TextoFecha=dd/MM/yyyy

Ejemplo
Para verificar si el 14 de octubre de 2019 es festivo:

http://localhost:54373/api/festivos?TextoFecha=14/10/2019

Respuestas
•  true: La fecha es un día festivo.

•  false: La fecha no es un día festivo.

•  {"Message": "Fecha no válida"}: La fecha proporcionada no es válida.