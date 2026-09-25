package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

import static modelo.certificacion.Certificable.ENTIDAD_EMISORA;

public class Curso extends Actividad implements Certificable {
    private int nivel;

    //--------------------------CONSTRUCTOR--------------------------
    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }


    //--------------------------METODOS--------------------------

    @Override
    public double calcularCostoMateriales() {
        return 0;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }


    public String generarCertificado(Estudiante estudiante) {
        return ("Certificado emitido por " + ENTIDAD_EMISORA + ": " +
                "Se deja constancia de que el estudiante " + estudiante.getNombre() +
                " asistió al curso " + this.getTitulo() + ".");
    }

//--------------------------GETTERS--------------------------

    public int getNivel() {
        return nivel;
    }

//--------------------------SETTERS--------------------------

    public void setNivel(int nivel) { this.nivel = nivel; }
}