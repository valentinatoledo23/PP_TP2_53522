import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        //--------------------------INSTANCIAS--------------------------

        //Creación de salas

        Sala sala1 = new Sala( //Agregacion: la sala se crea afuera de modelo.EventoUniversitario
                1,
                "Sala Multimedia"
        );

        Sala sala2 = new Sala(2, "Sala Tecnológica");

        //Creación de evento

        EventoUniversitario evento1 = new EventoUniversitario(
                "EV01",
                "Charla informativa sobre Java",
                25000,
                false
        );
        EventoUniversitario evento2 = new EventoUniversitario(
                "EV02",
                "El mundo de C++",
                10000,
                false
        );


        //Creación de actividades

        evento1.crearActividad(11, "Problemáticas actuales en el mundo digital", 35, "Charla", "Carlos Pereyra");
        evento1.crearActividad(23, "Programación Orientada a Objetos", 7, "Taller", true);
        evento2.crearActividad(77, "Programación en C++ desde 0", 15, "Curso", 1);

        //Asignacion de sala
        evento1.asignarSala(sala1); //Agregacion: la sala existe independientemente de modelo.EventoUniversitario
        evento2.asignarSala(sala2);


        //Copias del evento y actividades
        EventoUniversitario copiaEvento1 = new EventoUniversitario(evento1);
        EventoUniversitario copiaEvento2 = new EventoUniversitario(evento2);

        //--------------------------FILTRADO Y COSTOS--------------------------
        System.out.println("-------------------------------------------------");
        System.out.println("=== FILTRADO POR TIPO ===");

        List<Charla> charlas = evento1.filtrarActividadesPorTipo(Charla.class);
        List<Taller> talleres = evento1.filtrarActividadesPorTipo(Taller.class);
        List<Curso> cursos = evento2.filtrarActividadesPorTipo(Curso.class);

        System.out.println("Charlas en evento1: " + charlas.size());
        System.out.println("Talleres en evento1: " + talleres.size());
        System.out.println("Cursos en evento2: " + cursos.size());

        // Evidencia de que las listas están correctamente tipadas:
        for (Charla c : charlas) {
            System.out.println("Charla: " + c.getTitulo() + " (disertante: " + c.getDisertante() + ")");
        }
        for (Taller c: talleres) {
            System.out.println("Taller: " + c.getTitulo() + " (Requiere notebook? " + c.isRequiereNotebook());
        }
        for (Curso c: cursos) {
            System.out.println("Charla: " + c.getTitulo() + " (Nivel: " +  c.getNivel() + ")");
        }

        System.out.println("-------------------------------------------------");
        System.out.println("=== COSTO DE MATERIALES ===");
        System.out.println("Costo materiales charlas evento1: $" + evento1.calcularCostoMateriales(charlas));
        System.out.println("Costo materiales talleres evento1: $" + evento1.calcularCostoMateriales(talleres));
        System.out.println("Costo materiales cursos evento2: $" + evento2.calcularCostoMateriales(cursos));


        //Creacion de estudiantes

        Estudiante estudiante1= new Estudiante(
                "53442",
                "Juan Olivieri"
        );

        Estudiante estudiante2= new Estudiante(
                "53647",
                "Albana Mattos"
        );

        Estudiante estudiante3= new Estudiante(
                "53772",
                "Jimena Perez"
        );

        Estudiante estudiante4= new Estudiante(
                "54102",
                "Francisco Cinquemani"
        );

        Estudiante estudiante5= new Estudiante(
                "34425",
                "Sofia Mendez"
        );

        Estudiante estudiante6= new Estudiante(
                "53698",
                "Diego Pereyra"
        );
        Estudiante estudiante7= new Estudiante(
                "53522",
                "Valentina Toledo"
        );
        Estudiante estudiante8= new Estudiante(
                "53220",
                "Josefina Gonzalez"
        );

        //--------------------------LISTAS--------------------------

        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);
        estudiantes.add(estudiante4);
        estudiantes.add(estudiante5);
        estudiantes.add(estudiante6);
        estudiantes.add(estudiante7);
        estudiantes.add(estudiante8);

        System.out.println("-------------------------------------------------");
        System.out.println("=== ESTADO DE INSCRIPCIONES ===");

        try {
            evento1.getActividades().get(0).inscribir(estudiante1); //get(0) representa a modelo.actividades.Charla porque se creo primero
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento1.getActividades().get(0).inscribir(estudiante2);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento1.getActividades().get(1).inscribir(estudiante1); //get(1) representa a modelo.actividades.Taller porque se creo segundo
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento1.getActividades().get(1).inscribir(estudiante3);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento1.getActividades().get(1).inscribir(estudiante2);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento1.getActividades().get(1).inscribir(estudiante4);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento1.getActividades().get(1).inscribir(estudiante5);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento1.getActividades().get(1).inscribir(estudiante6);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento1.getActividades().get(1).inscribir(estudiante7);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento2.getActividades().get(0).inscribir(estudiante3);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }
        try {
            evento2.getActividades().get(0).inscribir(estudiante7);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");


            System.out.println("=== ERROR DE INSCRIPCIÓN: CUPO EXCEDIDO ===");

        try {
            evento1.getActividades().get(1).inscribir(estudiante8);
            System.out.println("La inscripción se ha realizado correctamente.");
        } catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Flujo de inscripcipon finalizado.");
            System.out.println("-------------------------------------------------");
        }

        //--------------------------FLUJO TRY-CATCH-FINALLY--------------------------
            System.out.println("-------------------------------------------------");
            System.out.println("=== FLUJO DE PERSISTENCIA ===");

        try {
            evento1.getActividades().get(0).inscribir(estudiante4);
            System.out.println("Inscripción exitosa en el flujo principal.");

            evento1.persistirEvento();
            System.out.println("Evento persistido correctamente.");

            EventoUniversitario eventoLeido = evento1.recuperarEvento("EV01");
            System.out.println("Evento leído. Título: " + eventoLeido.getTitulo());

        } catch (CupoExcedidoException e) {
            System.out.println("Catch CupoExcedidoException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Catch IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Catch ClassNotFoundException: " + e.getMessage());
        } finally {
            System.out.println("Finally: el flujo de persistencia finalizó.");
            System.out.println("-------------------------------------------------");

        }


        //--------------------------MUESTRAS DE DATOS--------------------------
        evento1.mostrarDatos();
        System.out.println("-------------------------------------------------");
        System.out.println("=== COPIA DE EVENTOS ===");
        copiaEvento1.mostrarDatos();

        evento2.mostrarDatos();
        System.out.println("-------------------------------------------------");
        System.out.println("=== COPIA DE EVENTO ===");
        copiaEvento2.mostrarDatos();

            System.out.println("-------------------------------------------------");
            System.out.println("Total de eventos creados: " + EventoUniversitario.getCantidadEventos());
    }
    }
}