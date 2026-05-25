package TPJAVA.domain;

public class InscripcionOyente extends Inscripcion{

    //agregar posibles metodos
    InscripcionOyente(Asignatura asignatura, Alumno alumno){
        super(asignatura, alumno);
    }
    @Override
    public boolean Promociona(){ // el oyente no puede promocionar
        return false;
    }
    public boolean Habilita(){ // el oyente no puede habilitar
        return false;
    }
}
