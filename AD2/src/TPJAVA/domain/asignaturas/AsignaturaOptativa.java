package TPJAVA.domain.asignaturas;

public class AsignaturaOptativa extends Asignatura{
    @Override
    public boolean cumpleCondicionPromocion(int asistencias, float condicion){
        return getPromocionable() && ((double) asistencias / getClasesTotales()) >= 0.6 * condicion;
    }
    @Override
    public boolean cumpleCondicionHabilita(int asistencias, float condicion){
        return ((double) asistencias / getClasesTotales()) >= 0.5 * condicion;
    }
    AsignaturaOptativa(String cod, String nombre, boolean promocionable, int cuatrimestre, int clasesTotales){
        super(cod, nombre, promocionable, cuatrimestre, clasesTotales);
    }
}
