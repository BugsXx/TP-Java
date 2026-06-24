package TPJAVA.domain.inscripciones;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;

public class InscripcionRegular extends Inscripcion {
    //agregar posibles metodos

    public InscripcionRegular(Asignatura asignatura, Alumno alumno){
        super(asignatura, alumno);
    }

    @Override
    public boolean promociona(){
        return getAsignatura().cumpleCondicionPromocion(getAsistencias(), 1);
    }

    @Override
    public boolean habilita(){
        return getAsignatura().cumpleCondicionHabilita(getAsistencias(), 1);
    }

    @Override
    public String modalidad(){
        return "Regular";
    }
}
