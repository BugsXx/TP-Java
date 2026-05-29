/**
 * YA QUE VAMOS A TENER QUE USAR UNA INTERFAZ GRAFICA (Swing es lo mas facil) PARA MOSTRAR VAMOS A TENER QUE USAR
 * METODOS QUE DEVUELVAN STRINGS. CON EL FORMATO "System.out.println(...)" SOLO PODEMOS MOSTRAR EN LA CONSOLA.
 */

package TPJAVA.domain;

import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.Iterator;
import java.util.LinkedList;

public class Universidad { //CLASE CONTROLADORA UNIVERSIDAD
    private LinkedList<Asignatura> asignaturas; //LISTA DE ASIGNATRAS

    Universidad(){
        asignaturas = new LinkedList<>();
    } //CONSTRUCTOR DE CLASE Universidad
    public void agregarAsignatura(String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales){
        Asignatura asignatura = new Asignatura(cod,nombre,promocionable,cuatrimestre,tipo, clasesTotales); //CREA OBJETO asignatura
        asignaturas.add(asignatura); //AÑADE OBJETO asignatura A LA LISTA
    }

    public void detalleCatedraLibres(String cod, int C1, int C2){
        System.out.println("Detalle de los alumnos libres%n");

        Iterator<Asignatura> it = asignaturas.iterator(); //DECLARAMOS ITERADOR PARA RECORRER LISTA DE ASIGNATURAS
        while(it.hasNext()) {
            Asignatura AsignaturaActual = it.next();
            int cuatrimestre = AsignaturaActual.getCuatrimestre();
            if(cuatrimestre >= C1 && cuatrimestre <= C2){

                Iterator<Inscripcion> itAsig = AsignaturaActual.getInscripciones().iterator(); //ITERADOR PARA RECORRER LISTA DE INSCRIPCIONES
                while (itAsig.hasNext()) {
                    Inscripcion inscripcionActual = itAsig.next();
                    if(inscripcionActual.Condicion().equals("Libre")){
                        inscripcionActual.getAlumno().muestra();
                        System.out.println("%n"); //POSIBLE CORRECION - CADA VEZ QUE MUESTRA, YA REALIZA EL SALTO DE LINEA YA QUE ES ln
                    }
                }
            }
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
