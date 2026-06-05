package TPJAVA.domain.inscripciones;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;

public class InscripcionCondicional extends Inscripcion {


    //agregar posibles metodos
    public InscripcionCondicional(Asignatura asignatura, Alumno alumno){
        super(asignatura, alumno);
    }
    @Override
    public boolean Promociona(){
        Asignatura asignatura = getAsignatura();
        return asignatura.cumpleCondicionHabilita(getAsistencias(), 1.2F);

    }

    @Override
    public boolean Habilita() {
        Asignatura asignatura = getAsignatura();
        return asignatura.cumpleCondicionHabilita(getAsistencias(), 1.2F);
    }

    public String Modalidad(){
        return "Condicional";
    }
}
