package TPJAVA.domain;

import TPJAVA.domain.asignaturas.Asignatura;
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

    public void detalleCatedra(String cod){
        Iterator<Asignatura> it = asignaturas.iterator();
        Asignatura asignaturaActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza

        while (asignaturaActual != null && !asignaturaActual.getCod().equals(cod)) {
            asignaturaActual = it.hasNext() ? it.next() : null; // es el ultimo? si es el ultimo, asignamos null al sig, si no, seguimos buscando
        }

        if (asignaturaActual != null) { // lo encontramos?
            Iterator<Inscripcion> itAsig = asignaturaActual.getInscripciones().iterator();
            Inscripcion inscripcionActual = itAsig.hasNext() ? itAsig.next(): null;

            System.out.println("Nombre de la asignatura: " + asignaturaActual.getNombre() +"\n");
            while(inscripcionActual != null){ // listamos todos los alumnos con sus clases asistidas
                System.out.println("Datos del alumno:\n");
                System.out.printf("\t");
                inscripcionActual.getAlumno().muestra();
                System.out.println();

                System.out.printf("\t");
                inscripcionActual.muestraClases();

                System.out.println("\tPorcentaje de asistencia: "+ inscripcionActual.getAsistencias()/asignaturaActual.getClasesTotales() * 100);
                System.out.println("\tModalidad: " + inscripcionActual.Modalidad());
                System.out.println("\tCondicion Academica: " + inscripcionActual.Condicion());

                inscripcionActual = itAsig.hasNext() ? itAsig.next() : null;
            }
        } else {
            System.out.println("La asignatura no existe");
        }
    }
}
