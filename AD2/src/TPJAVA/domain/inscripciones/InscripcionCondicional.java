package TPJAVA.domain.inscripciones;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;

public class InscripcionCondicional extends Inscripcion {

    public InscripcionCondicional(Asignatura asignatura, Alumno alumno){
        super(asignatura, alumno);
    }

    @Override
    public boolean promociona(){
        return getAsignatura().cumpleCondicionPromocion(getAsistencias(), 1.2F);
    }

    @Override
    public boolean habilita() {
        return getAsignatura().cumpleCondicionHabilita(getAsistencias(), 1.2F);
    }

    @Override
    public String modalidad(){
        return "Condicional";
    }
}
