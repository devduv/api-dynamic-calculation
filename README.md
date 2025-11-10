# API Cálculo Dinámico con Porcentaje Adicional

API REST desarrollada en **Java 21** y **Spring Boot 3.4.11** y PostgreSQL.

## Resumen
Realiza el cálculo de dos números sumando y agregando un porcentaje adicional.
- El porcentaje adicional es obtenido desde un servicio externo simulado (mock). 
- Se guarda el porcentaje en caché durante un periodo de tiempo. 
- Si el servicio externo falla se usa el valor guardado en caché, de no existir se devuelve error. 

Contiene configuración para desplegar en Docker mediante **docker-compose**.

---

## Ejecución del Proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/devduv/api-dynamic-calculation
cd api-dynamic-calculation
```
Imagen del proyecto en Docker Hub:

```bash
docker pull devduv/api-dynamic-calculation:0.0.1
```

---

### 2. Compilar el proyecto con Maven

- Tener instalado JDK 21.
- Maven 3.9+

Ejecutar el siguiente comando:
```bash
mvn clean install
```

Generará el archivo `.jar` dentro de `target/`.

---

### 3. Construir las imágenes Docker

Construir las imágenes definidas en el `docker-compose.yml`:

```bash
docker-compose build
```

---

### 4. Levantar los contenedores

Inicia la aplicación y la base de datos PostgreSQL con:

```bash
docker-compose up
```

> Por defecto, la API se ejecutará en:  
> **http://localhost:8080**

---

## Base de Datos

El servicio utiliza **PostgreSQL** como base de datos.  
Los parámetros por defecto son:

| Propiedad        | Valor              |
|------------------|--------------------|
| **Database**     | `dbpruebatecnica`  |
| **Usuario**      | `postgres`         |
| **Contraseña**   | `postgres`         |
| **Puerto**       | `5432`             |

---

## Contrato OpenAPI / Swagger

El contrato del API se encuentra disponible en:

- Archivo local: [`./openapi.yml`](./openapi.yml)  
- Documentación interactiva Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Configuración de tiempo máximo de expiración
En el archivo application.yml (properties) se configura el
parámetro "max-expiration-time". Tiempo máximo en minutos
para tener almacenado en memoria caché el valor del porcentaje.
```
max-expiration-time: 1
```
---
## Endpoints

### **GET /numbers/dynamic-calculation**

Obtiene el resultado del cálculo dinámico entre dos números aplicando un porcentaje adicional obtenido desde el servicio externo simulado.

**Ejemplo:**
```curl
curl --location 'http://localhost:8080/numbers/dynamic-calculation?num1=6&num2=4'
```

**Ejemplo Response:**
```json
{
  "result": 190.0
}
```

---

### **GET /request-history**

Obtiene el historial de solicitudes realizadas al endpoint `/numbers/dynamic-calculation`.

**Ejemplo de uso:**
```bash
curl --location 'http://localhost:8080/request-history?page=0&size=4'
```

**Ejemplo Response:**
```json
{
  "totalPages": 1,
  "totalElements": 1,
  "content": [
    {
      "date": "2025-11-10T07:19:34.617898",
      "endpoint": "/v1/numbers/dynamic-calculation",
      "parameters": {
        "num1": 6,
        "num2": 4
      },
      "response": 18.70,
      "status": "OK"
    }
  ]
}
```

## Desarrollador

**Brandon Duvan Saenz Falcon**  
Senior Java Developer | Contacto: duvanbradbrid@gmail.com
