package TPJAVA.domain.asignaturas;


import TPJAVA.domain.Alumno;
import TPJAVA.domain.wrappers.MutableBoolean;

import java.util.LinkedList;
import java.util.List;

public class Clase extends Asignatura { // CLASE DE X DIA, NO UN TURNO

    private List<Alumno> asistencia; // lista de Alumnos que asistieron a la clase
    private String id;
    private String fechaYHoraDictado;

    private boolean presencial;


    public String getId(){
        return id;
    }
    public String getFechaYHoraDictado(){
        return fechaYHoraDictado;
    }
    public Clase (String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales,String id, String fechaYHoraDictado){
        super(cod, nombre, promocionable, cuatrimestre, tipo, clasesTotales);
        asistencia = new LinkedList<>();
        this.id = id;
        this.fechaYHoraDictado = fechaYHoraDictado;
    }

    public boolean getPresencial(){
        return presencial;
    }
    public void tomaAsistencia(Alumno alumno){
        MutableBoolean result = new MutableBoolean(false);
        cargaAsistencia(alumno, this, result); // si esta inscripto, aumenta 1 a la asistencia de la lista de asignatura, agrega la clase a la lista de clases asistidas y devuelve true en result
        if(result.equals(true)){ // si esta inscripto
            asistencia.add(alumno); // agregamos a la lista de asistencia
        }
    }

    @Override

    public void muestra(){
    }

}

