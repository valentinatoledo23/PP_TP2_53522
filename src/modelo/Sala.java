package modelo;

import java.io.Serializable;

public class Sala implements Serializable {
        private int id;
        private String nombre;

    //--------------------------CONSTRUCTOR--------------------------

    public Sala(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //--------------------------METODOS--------------------------

    //--------------------------GETTERS--------------------------
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    //--------------------------SETTERS--------------------------

    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}