package TPJAVA.domain;


import java.util.ArrayList;
import java.util.List;

public class Clase extends Asignatura{ // CLASE DE X DIA, NO UN TURNO

    private List<Alumno> asistencia; // lista de Alumnos que asistieron a la clase
    private String id;
    private String fechaYHoraDictado;

    //agregar posibles metodos

    Clase(String id, String fechaYHoraDictado){
        asistencia = new ArrayList<>();
        this.id = id;
        this.fechaYHoraDictado = fechaYHoraDictado;
    }
    public void tomaAsistencia(Alumno alumno){
        asistencia.add(alumno); // agregamos a la lista de asistencia
        cargaAsistencia(alumno); // aumenta 1 a la asistencia de la lista de asignatura
    }

}

