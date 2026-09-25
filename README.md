# PP_TP2_53522 — Sistema de Gestión de Eventos Universitarios


## Descripción del proyecto

Aplicación en Java que modela un sistema de gestión de eventos universitarios, sus actividades, estudiantes e inscripciones. El proyecto incorpora manejo de excepciones, persistencia mediante serialización, interfaces, métodos parametrizados con wildcards y concurrencia con hilos.


## Estructura de clases

- **`EventoUniversitario`**: representa un evento universitario. Se compone de una o más actividades (**composición**: las actividades no existen sin el evento) y tiene asociada una sala (**agregación**: la sala existe independientemente del evento). Permite crear actividades de tipo `Charla`, `Taller` y `Curso` mediante sobrecarga de métodos, y persistir/recuperar el evento con serialización.

- **`Actividad`** (clase abstracta): clase base de toda actividad. Define atributos y comportamiento común (id, título, cupo máximo, lista de inscripciones, método `inscribir()`, `mostrarInscripciones()`). El método `mostrarIdentificacion()` es `final` (no puede redefinirse en las subclases). Los métodos `calcularCostoMateriales()` y `getTipo()` son abstractos: cada subclase concreta decide cómo implementarlos. El método `inscribir()` lanza `CupoExcedidoException` si se intenta superar el cupo máximo.

- **`Charla`**: subclase de `Actividad`. Actividad sin costo de materiales. Tiene un atributo propio `disertante`. No es certificable.

- **`Taller`**: subclase de `Actividad`. Actividad con costo de materiales según si requiere notebook o no. Tiene un atributo propio `requiereNotebook`. Implementa `Certificable`, por lo que puede emitir certificados.

- **`Curso`**: subclase de `Actividad`. Actividad con un atributo propio `nivel`. Implementa `Certificable`, por lo que puede emitir certificados.

- **`Estudiante`**: representa a un estudiante que puede inscribirse en actividades.

- **`Inscripcion`**: asocia un estudiante con una actividad, registrando fecha y estado de la inscripción. Contiene la clase anidada miembro `TicketDeAcceso`, que se emite cuando la inscripción está confirmada.

- **`Sala`**: representa el espacio físico asignado a un evento.

- **`Certificable`** (interfaz): define el método `generarCertificado(Estudiante)` y la constante `ENTIDAD_EMISORA`. La implementan `Taller` y `Curso`, pero no `Charla`.

- **`CupoExcedidoException`** (en paquete `excepciones`): excepción chequeada que se lanza al superar el cupo máximo de una actividad.

  
## Polimorfismo

El método `mostrarIdentificacion()`, definido una única vez en `Actividad` (y marcado `final`), utiliza internamente el método abstracto `getTipo()`. Al recorrer una lista `List<Actividad>` que contiene objetos `Charla`, `Taller` y `Curso` indistintamente, cada objeto resuelve `getTipo()` según su propia clase real, sin necesidad de preguntar explícitamente de qué tipo es cada actividad (sin `if`).


## Reglas de negocio

- Si el evento es gratuito, su costo total estimado es `0`.
- Si el evento no es gratuito, el costo total se calcula como:
(costoBase + suma del costo de materiales de sus actividades) * 1.21


  (el 21% adicional corresponde a impuestos).

- **Charlas:** no generan costo de materiales ($0).
- **Talleres:** cuestan $5000 si requieren notebook, o $2000 si no la requieren.


## Manejo de excepciones y persistencia

- `inscribir()` lanza `CupoExcedidoException` (excepción chequeada) cuando se supera el cupo, y se maneja en `App` con `try-catch-finally`.
- `persistirEvento()` serializa el evento en un archivo `evento_<id>.dat` usando `try-with-resources`.
- `recuperarEvento(id)` deserializa el evento desde el archivo, lanzando `IOException` o `ClassNotFoundException` si algo falla.
- En `App` se implementa un flujo `try-catch-finally` que inscribe, persiste y recupera un evento, mostrando mensajes claros para cada `catch`.


## Métodos parametrizados y wildcards (Ejercicio 3)

- `public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo)`: filtra las actividades del evento por tipo concreto, devolviendo listas correctamente tipadas como `List<Charla>`, `List<Taller>` o `List<Curso>`.
- `public double calcularCostoMateriales(List<? extends Actividad> actividades)`: calcula el costo de materiales de una lista de actividades (o subtipos) usando wildcards.


## Concurrencia (Ejercicio 4)

- `TicketDeAcceso` es una clase anidada miembro de `Inscripcion`, y se emite solo cuando la inscripción está confirmada.
- `EnvioTicketsThread` (en el paquete `hilos`) ejecuta en un hilo separado el envío de todos los tickets de un evento, mientras el hilo principal continúa mostrando los datos del evento, sus actividades e inscripciones.


## Cómo ejecutar el proyecto

1. Clonar el repositorio desde la consola (cmd):

```bash
   git clone https://github.com/valentinatoledo23/PP_TP2_53522.git
```

2. Abrir la carpeta del proyecto con IntelliJ IDEA.
3. Ejecutar la clase `App` (contiene el método `main`) para ver la salida.


## Ejemplo de ejecución

El programa, al ejecutarse:

1. Crea estudiantes.
2. Crea eventos universitarios, cada uno con su sala y sus actividades (`Charla`, `Taller`, `Curso`).
3. Inscribe estudiantes en las actividades, manejando `CupoExcedidoException` cuando se supera el cupo.
4. Emite certificados para talleres y cursos (actividades certificables).
5. Filtra las actividades por tipo y calcula el costo de materiales de cada una.
6. Persiste y recupera un evento usando serialización, con un flujo `try-catch-finally`.
7. Genera tickets de acceso para inscripciones confirmadas y los envía de forma concurrente con un hilo.
8. Muestra los datos de cada evento, recorriendo sus actividades de forma polimórfica.

### Captura de la salida por consola

<img width="1762" height="797" alt="image" src="https://github.com/user-attachments/assets/15f65f39-858f-45c6-8863-886736a360b2" />
<img width="862" height="795" alt="image" src="https://github.com/user-attachments/assets/823a2805-2bee-4400-9ae6-ce9cde898e2f" />
<img width="761" height="767" alt="image" src="https://github.com/user-attachments/assets/e9847d3d-136c-4ea5-ae58-c096fefb27f3" />
<img width="1423" height="770" alt="image" src="https://github.com/user-attachments/assets/91cd6cf9-447e-4782-bf67-92dce4583397" />
<img width="1481" height="792" alt="image" src="https://github.com/user-attachments/assets/8f4fb6f4-18d7-4249-9e7c-5309611f0159" />
<img width="1386" height="756" alt="image" src="https://github.com/user-attachments/assets/35ff2105-b956-48d5-b0e0-c6d2154e6ef5" />


### Alumna:
Valentina Toledo - Legajo 53522
