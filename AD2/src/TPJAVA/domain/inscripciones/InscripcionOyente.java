package TPJAVA.domain.inscripciones;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;

public class InscripcionOyente extends Inscripcion {

    public InscripcionOyente(Asignatura asignatura, Alumno alumno){
        super(asignatura, alumno);
    }

    @Override
    public boolean Promociona(){ // el oyente no puede promocionar
        return false;
    }

    @Override
    public boolean Habilita(){ // el oyente no puede habilitar
        return false;
    }

    public String Modalidad(){
        return "Oyente";
    }

    public String Condicion(){
        return "Oyente";
    }
}
