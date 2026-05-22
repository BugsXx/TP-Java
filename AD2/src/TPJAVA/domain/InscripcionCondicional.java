package TPJAVA.domain;

public class InscripcionCondicional extends Inscripcion{
    //agregar metodos

    @Override
    public boolean Promociona(){
        Asignatura asignatura = getAsignatura();
        if (asignatura.getPromocionable()){
            if (asignatura.getTipo() == 'P'){ // si la clase es optativa, se promociona con el 60%
                return ((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.8;
            } else if (asignatura.getTipo() == 'O') { // si es obligatoria, se promociona con el 80%
                return (((double) getAsistencias() / asignatura.getClasesTotales()) == 1);
            }else return false; // si no, no se puede promocionar
        }
        else return false;
    }
    public boolean Habilita(){
        Asignatura asignatura = getAsignatura();
            if (asignatura.getTipo() == 'P'){ // si la clase es optativa, se habilita con el 50%
                return ((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.7;
            } else if (asignatura.getTipo() == 'O') { // si es obligatoria, se habilita con el 60%
                return (((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.8);
            }else return (((double) getAsistencias() / asignatura.getClasesTotales()) >= 0.95); // si no, se habilita con el 75%

    }
}
