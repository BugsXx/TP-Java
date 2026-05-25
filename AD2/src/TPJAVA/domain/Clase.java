package TPJAVA.domain;


import java.util.LinkedList;

public class Clase extends Asignatura{ // CLASE DE X DIA, NO UN TURNO

    private LinkedList<Alumno> asistencia; // lista de Alumnos que asistieron a la clase
    private String id;
    private String fechaYHoraDictado;

    //agregar posibles metodos

    Clase(String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales,String id, String fechaYHoraDictado){
        super(cod, nombre, promocionable, cuatrimestre, tipo, clasesTotales);
        asistencia = new LinkedList<>();
        this.id = id;
        this.fechaYHoraDictado = fechaYHoraDictado;
    }
    public void tomaAsistencia(Alumno alumno){
        asistencia.add(alumno); // agregamos a la lista de asistencia
        cargaAsistencia(alumno); // aumenta 1 a la asistencia de la lista de asignatura
    }

}

