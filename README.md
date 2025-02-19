# 🌊 API de Cruceros 🚢

Esta API permite gestionar información sobre cruceros, incluyendo sus rutas, navieras y puertos. 

## 📌 Tecnologías utilizadas
- **Java 17+**
- **Spring Boot 3.4.2**
- **MongoDB**
- **Maven**
## 📌 Endpoints disponibles

### 🚢 **Cruceros**
| Método  | Endpoint                                      | Descripción |
|---------|----------------------------------------------|-------------|
| `GET`   | `/api2/cruceros`                            | Obtiene todos los cruceros. |
| `GET`   | `/api2/cruceros/{id}`                       | Obtiene un crucero por su ID. |
| `GET`   | `/api2/cruceros/naviera/{nombreNaviera}`    | Obtiene los cruceros de una naviera específica. |
| `GET`   | `/api2/cruceros/orden/puntuacion`          | Obtiene todos los cruceros ordenados por puntuación (desc). |
| `GET`   | `/api2/cruceros/top5`                      | Obtiene los 5 cruceros mejor puntuados. |
| `GET`   | `/api2/cruceros/salida/{nombrePuerto}`     | Obtiene los cruceros que **salen** de un puerto. |
| `GET`   | `/api2/cruceros/llegada/{nombrePuerto}`    | Obtiene los cruceros que **llegan** a un puerto. |
| `GET`   | `/api2/cruceros/ruta?puertoSalida=XXX&puertoLlegada=YYY` | Obtiene los cruceros entre dos puertos específicos. |
| `POST`  | `/api2/cruceros/create?password=XXXXXX`    | **Crea un crucero** (Requiere contraseña). |

### ⚓ **Puertos**
| Método  | Endpoint                         | Descripción |
|---------|---------------------------------|-------------|
| `GET`   | `/api2/puertos/{nombrePuerto}`  | Obtiene el ID de un puerto por su nombre. |



## 📌 Endpoints disponibles

### 🌊 **Cruceros**

#### 🔹 Obtener todos los cruceros
**GET** `/api2/cruceros`

##### 📄 Ejemplo de respuesta:
```json
[
  {
    "id": "65d2f1a7a2b7c5e5f1d1a001",
    "nombre": "Oasis of the Seas",
    "naviera": "65d2f1a7a2b7c5e5f1d1b001",
    "capacidad_pasajeros": 5479
  }
]
```

#### 🔹 Obtener un crucero por ID
**GET** `/api2/cruceros/{id}`

##### 📄 Ejemplo de respuesta:
```json
{
  "id": "65d2f1a7a2b7c5e5f1d1a001",
  "nombre": "Oasis of the Seas",
  "naviera": "65d2f1a7a2b7c5e5f1d1b001",
  "capacidad_pasajeros": 5479
}
```

#### 🔹 Obtener cruceros por naviera
**GET** `/api2/cruceros/naviera/{nombreNaviera}`

##### 📄 Ejemplo de respuesta:
```json
[
  {
    "id": "65d2f1a7a2b7c5e5f1d1a002",
    "nombre": "Harmony of the Seas",
    "naviera": "65d2f1a7a2b7c5e5f1d1b001",
    "capacidad_pasajeros": 5000
  }
]
```

#### 🔹 Obtener cruceros ordenados por puntuación
**GET** `/api2/cruceros/orden/puntuacion`

##### 📄 Ejemplo de respuesta:
```json
[
  {
    "id": "65d2f1a7a2b7c5e5f1d1a003",
    "nombre": "Allure of the Seas",
    "puntuacion": 4.9
  },
  {
    "id": "65d2f1a7a2b7c5e5f1d1a002",
    "nombre": "Harmony of the Seas",
    "puntuacion": 4.8
  }
]
```

#### 🔹 Obtener los 5 mejores cruceros por puntuación
**GET** `/api2/cruceros/top5`

##### 📄 Ejemplo de respuesta:
```json
[
  {
    "id": "65d2f1a7a2b7c5e5f1d1a003",
    "nombre": "Allure of the Seas",
    "puntuacion": 4.9
  }
]
```

#### 🔹 Obtener cruceros que salen de un puerto
**GET** `/api2/cruceros/salida/{nombrePuerto}`

##### 📄 Ejemplo de respuesta:
```json
[
  {
    "id": "65d2f1a7a2b7c5e5f1d1a005",
    "nombre": "Independence of the Seas",
    "puerto_salida": "65d2f1a7a2b7c5e5f1d1c001"
  }
]
```

#### 🔹 Obtener cruceros que llegan a un puerto
**GET** `/api2/cruceros/llegada/{nombrePuerto}`

##### 📄 Ejemplo de respuesta:
```json
[
  {
    "id": "65d2f1a7a2b7c5e5f1d1a006",
    "nombre": "Adventure of the Seas",
    "puerto_llegada": "65d2f1a7a2b7c5e5f1d1c002"
  }
]
```

#### 🔹 Obtener cruceros por ruta
**GET** `/api2/cruceros/ruta?puertoSalida=XXX&puertoLlegada=YYY`

##### 📄 Ejemplo de respuesta:
```json
[
  {
    "id": "65d2f1a7a2b7c5e5f1d1a007",
    "nombre": "Explorer of the Seas",
    "puerto_salida": "65d2f1a7a2b7c5e5f1d1c003",
    "puerto_llegada": "65d2f1a7a2b7c5e5f1d1c004"
  }
]
```

#### 🔹 Crear un crucero (requiere contraseña de admin)
**POST** `/api2/cruceros/create?password=supersecreto123`

##### 📄 Cuerpo de la solicitud:
```json
{
  "nombre": "Harmony of the Seas",
  "naviera": "65d2f1a7a2b7c5e5f1d1b001",
  "año_construccion": 2016,
  "capacidad_pasajeros": 5479,
  "capacidad_tripulacion": 2394,
  "cubiertas": 16,
  "longitud": 362,
  "peso": 226963,
  "puntuacion": 4.8,
  "num_reviews": 2500,
  "imagen_url": "https://example.com/harmony.jpg",
  "rutas": [
    {
      "puerto_salida": "65d2f1a7a2b7c5e5f1d1c001",
      "puerto_llegada": "65d2f1a7a2b7c5e5f1d1c002",
      "fecha_salida": "2025-06-01T12:00:00.000+00:00",
      "fecha_llegada": "2025-06-10T08:00:00.000+00:00",
      "precio_base": 1800
    }
  ]
}
```

##### 📄 Ejemplo de respuesta (201 Created):
```json
{
  "id": "65d2f1a7a2b7c5e5f1d1a999",
  "nombre": "Harmony of the Seas",
  "naviera": "65d2f1a7a2b7c5e5f1d1b001",
  "capacidad_pasajeros": 5479
}
```

---

🔒 **Nota:** Este endpoint requiere una **contraseña de administrador** para la creación del crucero. Si la contraseña es incorrecta, la API responderá con **403 Forbidden**:

```json
{
  "error": "Acceso denegado: contraseña incorrecta."
}
```






### 📤 Crear un crucero

Para crear un crucero, se debe enviar una petición **POST** con la contraseña correcta en los parámetros.

#### 📌 Ejemplo de request:

```http
POST /api2/cruceros/create?password=supersecreto123
Content-Type: application/json
```

#### 📄 Cuerpo de la solicitud (JSON):

```json
{
  "nombre": "Harmony of the Seas",
  "naviera": "65d2f1a7a2b7c5e5f1d1b001",
  "año_construccion": 2016,
  "capacidad_pasajeros": 5479,
  "capacidad_tripulacion": 2394,
  "cubiertas": 16,
  "longitud": 362,
  "peso": 226963,
  "puntuacion": 4.8,
  "num_reviews": 2500,
  "imagen_url": "https://example.com/harmony.jpg",
  "rutas": [
    {
      "puerto_salida": "65d2f1a7a2b7c5e5f1d1c001",
      "puerto_llegada": "65d2f1a7a2b7c5e5f1d1c002",
      "fecha_salida": "2025-06-01T12:00:00.000+00:00",
      "fecha_llegada": "2025-06-10T08:00:00.000+00:00",
      "precio_base": 1800
    }
  ]
}
```

#### 📌 Respuesta esperada (**201 Created**):

```json
{
  "id": "65d2f1a7a2b7c5e5f1d1a999",
  "nombre": "Harmony of the Seas",
  "naviera": "65d2f1a7a2b7c5e5f1d1b001",
  "capacidad_pasajeros": 5479
}
```

---

🔐 **Nota:** Este endpoint requiere una **contraseña de administrador** para la creación del crucero. La contraseña debe enviarse como un parámetro en la URL:

```http
POST /api2/cruceros/create?password=supersecreto123
```

Si la contraseña es incorrecta, la API responderá con **403 Forbidden**:

```json
{
  "error": "Acceso denegado: contraseña incorrecta."
}
```

---



