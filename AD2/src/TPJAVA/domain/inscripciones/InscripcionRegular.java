package TPJAVA.domain.inscripciones;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;

public class InscripcionRegular extends Inscripcion {
    //agregar posibles metodos

    public InscripcionRegular(Asignatura asignatura, Alumno alumno){
        super(asignatura, alumno);
    }
    @Override
    public boolean Promociona(){
        return getAsignatura().cumpleCondicionPromocion(getAsistencias(), 1);
    }
    @Override
    public boolean Habilita(){
        return getAsignatura().cumpleCondicionHabilita(getAsistencias(), 1);
    }

    @Override
    public String Modalidad(){
        return "Regular";
    }
}
