package TPJAVA.domain.asignaturas;

import TPJAVA.domain.inscripciones.TipoMateria;

public class PasantiaYTesis extends Asignatura{

    @Override
    public boolean cumpleCondicionPromocion(int asistencias, float condicion){
        return false;
    }

    @Override
    public boolean cumpleCondicionHabilita(int asistencias, float condicion){
        return (((double) asistencias / getClasesTotales()) >= TipoMateria.PasantiaHabilita.getValor() * condicion);
    }

    public PasantiaYTesis(String cod, String nombre, boolean promocionable, int cuatrimestre, int clasesTotales){
        super(cod, nombre, promocionable, cuatrimestre, clasesTotales);
    }

}
