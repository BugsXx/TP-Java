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
        if (asignatura.getPromocionable()){
            if (asignatura.getTipo() == 'P'){ // si la clase es optativa, se promociona con el 60%
                return ((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.6;
            } else if (asignatura.getTipo() == 'O') { // si es obligatoria, se promociona con el 80%
                return (((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.8);
            }else return false; // si no, no se puede promocionar
        }
        else return false;
    }
    public boolean Habilita(){
        Asignatura asignatura = getAsignatura();
        if (asignatura.getTipo() == 'P'){ // si la clase es optativa, se habilita con el 50%
            return ((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.5;
        } else if (asignatura.getTipo() == 'O') { // si es obligatoria, se habilita con el 60%
            return (((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.6);
        }else return (((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.75); // si no, se habilita con el 75%

    }
    public String Modalidad(){
        return "Regular";
    }
}
