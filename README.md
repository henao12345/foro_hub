# foro_hub
Un foro es un lugar donde todos los participantes de una plataforma pueden colocar sus preguntas sobre determinados asuntos.
# ForoHub - Documentación de la Aplicación

## Descripción General
ForoHub es una aplicación web diseñada para gestionar tópicos o temas de discusión en foros. Los usuarios pueden crear, listar, editar y eliminar tópicos, así como consultar detalles específicos de cada tópico. La aplicación también permite organizar y filtrar los tópicos según criterios como el curso y el año de creación.

## Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 3.x**
  - Spring Data JPA
  - Spring Web
  - Spring Security
- **Base de datos**: H2 (para pruebas y desarrollo) o cualquier base de datos compatible con JPA
- **Maven**: para la gestión de dependencias

## Características de la Aplicación
1. **Crear Tópico**: Permite registrar un nuevo tópico con los siguientes atributos:
   - Título
   - Descripción
   - Estado (Activo o Inactivo)
   - Autor
   - Curso
   - Fecha de creación (asignada automáticamente)

2. **Listar Tópicos**:
   - Listar todos los tópicos.
   - Listar los primeros 10 tópicos ordenados por fecha de creación en orden ascendente.
   - Filtrar tópicos por curso y año específico.

3. **Consultar Detalles de un Tópico**: Recuperar información detallada de un tópico utilizando su ID.

4. **Actualizar Tópico**: Modificar los datos de un tópico existente mediante su ID.

5. **Eliminar Tópico**: Borrar un tópico específico de la base de datos utilizando su ID.

## Endpoints Disponibles
| Método HTTP | URI                 | Descripción                                |
|---------------|---------------------|--------------------------------------------|
| POST          | `/api/topicos`      | Crear un nuevo tópico                     |
| GET           | `/api/topicos`      | Listar todos los tópicos                   |
| GET           | `/api/topicos/ordenados` | Listar los primeros 10 tópicos por fecha    |
| GET           | `/api/topicos/{id}` | Consultar detalles de un tópico por ID     |
| GET           | `/api/topicos/curso/{curso}/anio/{anio}` | Filtrar tópicos por curso y año         |
| PUT           | `/api/topicos/{id}` | Actualizar un tópico por ID               |
| DELETE        | `/api/topicos/{id}` | Eliminar un tópico por ID                 |

## Configuración de Seguridad
La aplicación utiliza Spring Security para la gestión de autenticación y autorización. Actualmente:
- Los endpoints que inician con `/api/topicos` son de acceso público (sin autenticación).
- Otros endpoints requieren autenticación.
- Se utiliza un usuario en memoria para pruebas:
  - **Usuario**: cesar
  - **Contraseña**: password123

## Estructura del Proyecto
```
com.cesar.foro.demo
├── config/                # Configuración de seguridad y Beans
├── controller/            # Controladores REST
├── dto/                   # Clases de transferencia de datos
├── entity/                # Entidades del modelo de datos
├── repository/            # Interfaces JPA para acceso a datos
├── service/               # Lógica de negocio
└── ForoHubApplication.java # Clase principal
```

## Próximos Desafíos
1. Implementar pruebas unitarias y de integración.
2. Agregar soporte para paginación y búsquedas avanzadas.
3. Mejorar la configuración de seguridad con usuarios y roles dinámicos.
4. Desplegar la aplicación en un entorno de producción (ejemplo: Heroku, AWS, GCP).

## Ejecución del Proyecto
1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/forohub.git
   ```

2. Navega al directorio del proyecto:
   ```bash
   cd foro_hub
   ```

3. Compila y ejecuta la aplicación con Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Accede a la aplicación desde tu navegador en [http://localhost:8080](http://localhost:8080).

---
Con esta documentación inicial, esperamos que puedas comprender y utilizar ForoHub de manera efectiva. 

