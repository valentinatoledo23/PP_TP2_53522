# PP_TP2_53522 — Sistema de Gestión de Eventos Universitarios

## Descripción del proyecto

Aplicación en Java que modela un sistema de gestión de eventos universitarios, sus actividades, estudiantes e inscripciones. El proyecto incorpora manejo de excepciones, persistencia mediante serialización, interfaces, métodos parametrizados con wildcards y concurrencia con hilos.

## Estructura de clases

| Clase | Descripción |
|---|---|
| `EventoUniversitario` | Representa un evento universitario. Se compone de una o más actividades (**composición**: las actividades no existen sin el evento) y tiene asociada una sala (**agregación**: la sala existe independientemente del evento). Permite crear actividades de tipo `Charla`, `Taller` y `Curso` mediante sobrecarga de métodos, y persistir/recuperar el evento con serialización. |
| `Actividad` (clase abstracta) | Clase base de toda actividad. Define atributos y comportamiento común (id, título, cupo máximo, lista de inscripciones, método `inscribir()`, `mostrarInscripciones()`). El método `mostrarIdentificacion()` es `final` (no puede redefinirse en las subclases). Los métodos `calcularCostoMateriales()` y `getTipo()` son abstractos: cada subclase concreta decide cómo implementarlos. El método `inscribir()` lanza `CupoExcedidoException` si se intenta superar el cupo máximo. |
| `Charla` | Subclase de `Actividad`. Actividad sin costo de materiales. Tiene un atributo propio `disertante`. No es certificable. |
| `Taller` | Subclase de `Actividad`. Actividad con costo de materiales según si requiere notebook o no. Tiene un atributo propio `requiereNotebook`. Implementa `Certificable`, por lo que puede emitir certificados. |
| `Curso` | Subclase de `Actividad`. Actividad con un atributo propio `nivel`. Implementa `Certificable`, por lo que puede emitir certificados. |
| `Estudiante` | Representa a un estudiante que puede inscribirse en actividades. |
| `Inscripcion` | Asocia un estudiante con una actividad, registrando fecha y estado de la inscripción. Contiene la clase anidada miembro `TicketDeAcceso`, que se emite cuando la inscripción está confirmada. |
| `Sala` | Representa el espacio físico asignado a un evento. |
| `Certificable` (interfaz) | Define el método `generarCertificado(Estudiante)` y la constante `ENTIDAD_EMISORA`. La implementan `Taller` y `Curso`, pero no `Charla`. |
| `CupoExcedidoException` (en paquete `excepciones`) | Excepción chequeada que se lanza al superar el cupo máximo de una actividad. |

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

![Salida de ejecución](ruta/a/tu/captura.png)

> Reemplazá la ruta con la imagen de tu ejecución (por ejemplo, `docs/salida.png`).
