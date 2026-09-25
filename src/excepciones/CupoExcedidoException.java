package excepciones;

public class CupoExcedidoException extends Exception {

    //--------------------------CONSTRUCTOR--------------------------

    public CupoExcedidoException() {
        super("No quedan cupos disponibles.");
    }

}
