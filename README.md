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
