package TPJAVA.domain.asignaturas;

public class PasantiaYTesis extends Asignatura{

    @Override
    public boolean cumpleCondicionPromocion(int asistencias, float condicion){
        return false;
    }

    @Override
    public boolean cumpleCondicionHabilita(int asistencias, float condicion){
        return (((double) asistencias / getClasesTotales()) >= 0.75 * condicion);
    }

    PasantiaYTesis(String cod, String nombre, boolean promocionable, int cuatrimestre, int clasesTotales){
        super(cod, nombre, promocionable, cuatrimestre, clasesTotales);
    }

}
