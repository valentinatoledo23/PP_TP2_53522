package modelo;

import modelo.actividades.Actividad;

import java.time.LocalDate;

import java.io.Serializable;

public class Inscripcion implements Serializable {
    private final Estudiante estudiante;
    private final Actividad actividad;
    private LocalDate fecha;
    private String estado;

    public Inscripcion(Estudiante estudiante, Actividad actividad, LocalDate fecha, String estado) {
        this.estudiante = estudiante;
        this.actividad = actividad;
        this.fecha = fecha;
        this.estado = estado;
        //Para asociacion se pasan los parametros de las clases que lo asocian y se agrega en el constructor
    }

    //--------------------------GETTERS--------------------------

    public Estudiante getEstudiante() {
        return estudiante;
    }
    public Actividad getActividad() {
        return actividad;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public String getEstado() {
        return estado;
    }
}
