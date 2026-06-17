package TPJAVA.domain.clase;


import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.clase.exceptions.AlumnoConPresenteException;
import TPJAVA.domain.inscripciones.Inscripcion;
import TPJAVA.domain.inscripciones.exceptions.NoEncuentraInscripcionException;

import java.util.LinkedList;
import java.util.List;

public class Clase { // CLASE DE X DIA, NO UN TURNO

    private List<Alumno> asistencia; // lista de Alumnos que asistieron a la clase
    private String id;
    private String fechaYHoraDictado;
    private Asignatura asignatura;


    public String getId(){
        return id;
    }
    public String getFechaYHoraDictado(){
        return fechaYHoraDictado;
    }

    public Clase ( String id, String fechaYHoraDictado, Asignatura asignatura){
        this.asignatura = asignatura;
        asistencia = new LinkedList<>();
        this.id = id;
        this.fechaYHoraDictado = fechaYHoraDictado;
    }


    public void tomaAsistencia(Alumno alumno) throws NoEncuentraInscripcionException, AlumnoConPresenteException{
        asignatura.cargaAsistencia(alumno, this);
        if(asistencia.contains(alumno))
            throw new AlumnoConPresenteException("El alumno ya tiene el presente");
        asistencia.add(alumno);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Clase)
            return ((Clase) obj).id.equals(this.id);
        else  return false;
    }
}







