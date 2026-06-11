/**
 * YA QUE VAMOS A TENER QUE USAR UNA INTERFAZ GRAFICA (Swing es lo mas facil) PARA MOSTRAR VAMOS A TENER QUE USAR
 * METODOS QUE DEVUELVAN STRINGS. CON EL FORMATO "System.out.println(...)" SOLO PODEMOS MOSTRAR EN LA CONSOLA.
 */

package TPJAVA.domain;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.asignaturas.exceptions.NoEncuentraAsignaturaException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Universidad {
    private static Universidad universidad;
    private TreeSet<Asignatura> asignaturas;
    private static List<Alumno> alumnos;
    private String nombre;


    public static Universidad getUniversidad(){
        if (universidad == null)
            universidad = new Universidad();
        return  universidad;
    }

    public static List<Alumno> getAlumnos(){
        if (alumnos == null)
            alumnos = new LinkedList<>();
        return  alumnos;
    }

    private Universidad(){

        asignaturas = new TreeSet<>();
        universidad = null;
        alumnos = null;
    }

    public void agregarAsignatura(String cod, String nombre, boolean promocionable, int cuatrimestre, int clasesTotales){
        // a desarrollar por que depende el tipo de asignatura a crear
    }

    public TreeSet getAsignaturas(){
        return asignaturas;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Asignatura encuentraAsignatura(String cod) throws NoEncuentraAsignaturaException{
        Iterator<Asignatura> it = asignaturas.iterator();
        Asignatura asignaturaActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza

        while (it.hasNext() && !asignaturaActual.getCod().equals(cod))
            asignaturaActual = it.next();
        if (asignaturaActual != null && asignaturaActual.getCod().equals(cod))
            return asignaturaActual;
        else throw new NoEncuentraAsignaturaException();
    }

}
