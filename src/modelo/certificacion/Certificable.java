package modelo.certificacion;

import modelo.Estudiante;

public interface Certificable {
    public String ENTIDAD_EMISORA = "UTN - FRM";

    //--------------------------METODOS--------------------------
    public String generarCertificado(Estudiante estudiante);


}
