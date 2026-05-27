package TPJAVA.domain;


import TPJAVA.domain.wrappers.MutableBoolean;

import java.util.LinkedList;

public class Clase extends Asignatura{ // CLASE DE X DIA, NO UN TURNO

    private LinkedList<Alumno> asistencia; // lista de Alumnos que asistieron a la clase
    private String id;
    private String fechaYHoraDictado;

    //agregar posibles metodos

    public String getId(){
        return id;
    }
    public String getFechaYHoraDictado(){
        return fechaYHoraDictado;
    }
    Clase(String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales,String id, String fechaYHoraDictado){
        super(cod, nombre, promocionable, cuatrimestre, tipo, clasesTotales);
        asistencia = new LinkedList<>();
        this.id = id;
        this.fechaYHoraDictado = fechaYHoraDictado;
    }
    public void tomaAsistencia(Alumno alumno){
        MutableBoolean result = new MutableBoolean();
        cargaAsistencia(alumno, result); // aumenta 1 a la asistencia de la lista de asignatura
        if(result.equals(true)){
            asistencia.add(alumno); // agregamos a la lista de asistencia
        }
    }

}

