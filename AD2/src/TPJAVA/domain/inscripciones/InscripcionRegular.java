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
        Asignatura asignatura = getAsignatura();
        return asignatura.cumpleCondicionPromocion(getAsistencias(), 1);
    }
    @Override
    public boolean Habilita(){
        Asignatura asignatura = getAsignatura();
        return asignatura.cumpleCondicionHabilita(getAsistencias(), 1);
    }
    public String Modalidad(){
        return "Regular";
    }
}
