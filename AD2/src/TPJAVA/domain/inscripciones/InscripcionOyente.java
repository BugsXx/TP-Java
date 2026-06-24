package TPJAVA.domain.inscripciones;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;

public class InscripcionOyente extends Inscripcion {

    public InscripcionOyente(Asignatura asignatura, Alumno alumno){
        super(asignatura, alumno);
    }

    @Override
    public boolean promociona(){ // el oyente no puede promocionar
        return false;
    }

    @Override
    public boolean habilita(){ // el oyente no puede habilitar
        return false;
    }

    @Override
    public String modalidad() { return "Oyente"; }

    @Override
    public String condicion(){
        return "Oyente";
    }
}
