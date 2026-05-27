package TPJAVA.domain.inscripciones;

import TPJAVA.domain.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;

public class InscripcionOyente extends Inscripcion {

    //agregar posibles metodos
    public InscripcionOyente(Asignatura asignatura, Alumno alumno){
        super(asignatura, alumno);
    }
    @Override
    public boolean Promociona(){ // el oyente no puede promocionar
        return false;
    }
    public boolean Habilita(){ // el oyente no puede habilitar
        return false;
    }

    public String Condicion(){
        return "Oyente";
    }
}
