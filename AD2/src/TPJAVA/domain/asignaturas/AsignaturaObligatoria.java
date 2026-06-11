package TPJAVA.domain.asignaturas;

import TPJAVA.domain.inscripciones.TipoMateria;

public class AsignaturaObligatoria extends Asignatura{
    @Override
    public boolean cumpleCondicionPromocion(int asistencias, float condicion){
        return getPromocionable() && (((double) asistencias/ getClasesTotales() >= TipoMateria.ObligatoriaPromocion.getValor() * condicion));

    }
    @Override
    public boolean cumpleCondicionHabilita(int asistencias, float condicion){
            return (((double) asistencias / getClasesTotales()) >= TipoMateria.ObligatoriaHabilita.getValor() * condicion);

    }
    public AsignaturaObligatoria(String cod, String nombre, boolean promocionable, int cuatrimestre, int clasesTotales){
        super(cod, nombre, promocionable, cuatrimestre, clasesTotales);
    }

}
