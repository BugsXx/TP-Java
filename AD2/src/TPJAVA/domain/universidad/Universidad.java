/**
 * YA QUE VAMOS A TENER QUE USAR UNA INTERFAZ GRAFICA (Swing es lo mas facil) PARA MOSTRAR VAMOS A TENER QUE USAR
 * METODOS QUE DEVUELVAN STRINGS. CON EL FORMATO "System.out.println(...)" SOLO PODEMOS MOSTRAR EN LA CONSOLA.
 */

package TPJAVA.domain.universidad;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.asignaturas.exceptions.NoEncuentraAsignaturaException;
import TPJAVA.domain.universidad.exceptions.AsignaturaExistenteException;
import TPJAVA.domain.universidad.exceptions.YaEstaInscriptoElAlumnoALaUniversidadException;
import com.sun.source.tree.Tree;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Universidad implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Universidad universidad;
    private TreeSet<Asignatura> asignaturas;
    private TreeSet<Alumno> alumnos;
    private String nombre;


    public static void setInstancia(Universidad uniDeserializada) {
        universidad = uniDeserializada;
    }
    public static Universidad getUniversidad(){
        if (universidad == null) {
            universidad = new Universidad();
        }
        return universidad;
    }
    public  TreeSet<Alumno> getAlumnos(){
        return  alumnos;
    }

    private Universidad(){
        asignaturas = new TreeSet<>();
        alumnos = new TreeSet<>();
    }

    public void agregarAsignatura(Asignatura asignatura)throws AsignaturaExistenteException {
        if(!asignaturas.contains(asignatura)){
            asignaturas.add(asignatura);
        }
        else throw new AsignaturaExistenteException("Ya existe la asignatura en la universidad");
    }

    public TreeSet<Asignatura> getAsignaturas(){
        return asignaturas;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Asignatura encuentraAsignatura(String cod) throws NoEncuentraAsignaturaException {
        if (cod == null) throw new NoEncuentraAsignaturaException();

        for (Asignatura asig : asignaturas) {
            if (asig.getCod().trim().equalsIgnoreCase(cod.trim())) {
                return asig;
            }
        }
        throw new NoEncuentraAsignaturaException();
    }


    public void agregaAlumno(Alumno alumno)throws YaEstaInscriptoElAlumnoALaUniversidadException{
        if(! alumnos.contains(alumno))
            alumnos.add(alumno);
        else throw new YaEstaInscriptoElAlumnoALaUniversidadException("El alumno ya esta inscripto en la universidad");
    }

}
