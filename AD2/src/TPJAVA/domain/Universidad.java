package TPJAVA.domain;

import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.Iterator;
import java.util.LinkedList;

public class Universidad {
    private LinkedList<Asignatura> asignaturas;


    Universidad(){
        asignaturas = new LinkedList<>();
    }
    public void agregarAsignatura(String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales){
        Asignatura asignatura = new Asignatura(cod,nombre,promocionable,cuatrimestre,tipo, clasesTotales);
        asignaturas.add(asignatura);
    }

    public void detalleCatedraLibres(String cod, int C1, int C2){
        System.out.println("Detalle de los alumnos libres%n");

        Iterator<Asignatura> it = asignaturas.iterator();

        Asignatura AsignaturaActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza

        while (AsignaturaActual != null) {
            if(AsignaturaActual.getCuatrimestre() >= C1 && AsignaturaActual.getCuatrimestre() <= C2){
                Iterator <Inscripcion> itAsig = AsignaturaActual.getInscripciones().iterator();
                Inscripcion inscripcionActual = itAsig.hasNext() ? itAsig.next() : null;

                while (inscripcionActual != null){
                    if(inscripcionActual.Condicion().equals("Libre")){
                        inscripcionActual.getAlumno().muestra();
                        System.out.println("%n");

                        
                    }
                    inscripcionActual = itAsig.hasNext() ? itAsig.next() : null;
                }


            }
            AsignaturaActual = it.hasNext() ? it.next() : null; // es el ultimo? si es el ultimo, asignamos null al sig, si no, seguimos buscando
        }


    }
}
