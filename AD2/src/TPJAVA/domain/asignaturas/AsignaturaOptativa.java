package TPJAVA.domain.asignaturas;

import TPJAVA.domain.inscripciones.TipoMateria;

public class AsignaturaOptativa extends Asignatura{
    @Override
    public boolean cumpleCondicionPromocion(int asistencias, float condicion){
        return getPromocionable() && ((double) asistencias / getClasesTotales()) >= TipoMateria.OptativaPromocion.getValor() * condicion;
    }
    @Override
    public boolean cumpleCondicionHabilita(int asistencias, float condicion){
        return ((double) asistencias / getClasesTotales()) >= TipoMateria.OptativaHabilita.getValor() * condicion;
    }
    AsignaturaOptativa(String cod, String nombre, boolean promocionable, int cuatrimestre, int clasesTotales){
        super(cod, nombre, promocionable, cuatrimestre, clasesTotales);
    }
}
