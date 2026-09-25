package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

//Taller hereda Serializable

import modelo.certificacion.Certificable;

public class Taller extends Actividad implements Certificable {
    private boolean requiereNotebook;

    //--------------------------CONSTRUCTOR--------------------------

    public Taller (int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo); //Se llama al constructor de modelo.actividades.Actividad
        this.requiereNotebook = requiereNotebook;
    }

    //--------------------------METODOS--------------------------

    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000 : 2000;
    }

    @Override
    public String getTipo() {
         return "Taller";
    }

    public String generarCertificado(Estudiante estudiante){
        return ("Certificado emitido por " + ENTIDAD_EMISORA + ": " +
                "Se deja constancia de que el estudiante " + estudiante.getNombre() +
                " asistió al taller " + this.getTitulo() + ".");
    }

    //--------------------------GETTERS--------------------------

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }

    //--------------------------SETTERS--------------------------

    public void setRequiereNotebook(boolean requiereNotebook) {
        this.requiereNotebook = requiereNotebook;
    }
}
