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

Puedes clonar el proyecto desde un repositorio de ejemplo:

```bash
git clone https://github.com/usuario/proyecto-calculo-dinamico.git
cd proyecto-calculo-dinamico
```

> También podrías descargar una imagen del proyecto desde Docker Hub (ejemplo):
> ```bash
> docker pull usuario/proyecto-calculo-dinamico:latest
> ```

---

### 2. Compilar el proyecto con Maven

Asegúrate de tener instalado **JDK 21** y **Maven 3.9+**, luego ejecuta:

```bash
mvn clean install
```

Esto compilará el proyecto y generará el archivo `.jar` dentro del directorio `target/`.

---

### 3. Construir las imágenes Docker

Ejecuta el siguiente comando para construir las imágenes definidas en el `docker-compose.yml`:

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

## Endpoints Disponibles

### **GET /numbers/dynamic-calculation**

Obtiene el resultado del cálculo dinámico entre dos números aplicando un porcentaje adicional obtenido desde el servicio externo simulado.

**Ejemplo de uso:**
```bash
GET http://localhost:8080/numbers/dynamic-calculation?number1=50&number2=100
```

**Ejemplo de respuesta:**
```json
{
  "number1": 50,
  "number2": 100,
  "randomPercentage": 27,
  "result": 190.0
}
```

---

### **GET /request-history**

Obtiene el historial de solicitudes realizadas al endpoint `/numbers/dynamic-calculation`.

**Ejemplo de uso:**
```bash
GET http://localhost:8080/request-history
```

**Ejemplo de respuesta:**
```json
[
  {
    "number1": 50,
    "number2": 100,
    "randomPercentage": 27,
    "result": 190.0,
    "timestamp": "2025-11-09T14:32:18"
  },
  {
    "number1": 20,
    "number2": 80,
    "randomPercentage": 10,
    "result": 110.0,
    "timestamp": "2025-11-09T14:40:22"
  }
]
```

---

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.4.11**
- **Maven**
- **Docker / Docker Compose**
- **PostgreSQL**
- **OpenAPI / Swagger**

---

## Estructura del Proyecto

```
├── src/
│   ├── main/
│   │   ├── java/...
│   │   └── resources/
│   └── test/
├── openapi.yml
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## Notas Técnicas

- El mock de porcentaje se encuentra implementado como un **servicio simulado** dentro del mismo proyecto.
- El cálculo se realiza dinámicamente en cada request, por lo tanto el porcentaje puede variar en cada ejecución.
- El historial de solicitudes se almacena en la base de datos PostgreSQL.

---

## Desarrollador

**Brandon Duvan Saenz Falcon**  
Senior Java Developer | Contacto: duvanbradbrid@gmail.com
