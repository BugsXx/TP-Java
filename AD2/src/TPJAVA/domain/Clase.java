package TPJAVA.domain;


import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.inscripciones.exceptions.NoEncuentraInscripcionException;

import java.util.LinkedList;
import java.util.List;

public class Clase { // CLASE DE X DIA, NO UN TURNO

    private List<Alumno> asistencia; // lista de Alumnos que asistieron a la clase
    private String id;
    private String fechaYHoraDictado;
    private Asignatura asignatura;
    private boolean presencial;


    public String getId(){
        return id;
    }
    public String getFechaYHoraDictado(){
        return fechaYHoraDictado;
    }
    public Clase ( String id, String fechaYHoraDictado){
        asistencia = new LinkedList<>();
        this.id = id;
        this.fechaYHoraDictado = fechaYHoraDictado;
    }

    public boolean getPresencial(){
        return presencial;
    }

    public void tomaAsistencia(Alumno alumno){
        try{
            asignatura.cargaAsistencia(alumno, this);
            asistencia.add(alumno);
        }
        catch(NoEncuentraInscripcionException e){
            e.getMessage();
        }
    }





}

