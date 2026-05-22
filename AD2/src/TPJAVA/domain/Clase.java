package TPJAVA.domain;


import java.util.ArrayList;
import java.util.List;

public class Clase extends Asignatura{ // CLASE DE X DIA, NO UN TURNO

    private List<Alumno> asistencia; // lista de Alumnos que asistieron a la clase
    private String id;
    private String fechaYHoraDictado;

    //agregar posibles metodos

    Clase(){
        asistencia = new ArrayList<>();
    }
    public void tomaAsistencia(Alumno alumno){
        asistencia.add(alumno);
        cargaAsistencia(alumno);
    }

}

