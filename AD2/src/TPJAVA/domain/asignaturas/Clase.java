package TPJAVA.domain.asignaturas;


import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.inscripciones.exceptions.NoEncuentraInscripcionException;

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
        try{
            cargaAsistencia(alumno, this);
            asistencia.add(alumno);
        }
        
        catch(NoEncuentraInscripcionException e){

        }
    }





}

