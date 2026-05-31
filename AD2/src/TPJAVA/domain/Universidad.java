/**
 * YA QUE VAMOS A TENER QUE USAR UNA INTERFAZ GRAFICA (Swing es lo mas facil) PARA MOSTRAR VAMOS A TENER QUE USAR
 * METODOS QUE DEVUELVAN STRINGS. CON EL FORMATO "System.out.println(...)" SOLO PODEMOS MOSTRAR EN LA CONSOLA.
 */

package TPJAVA.domain;

import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.inscripciones.Inscripcion;
import java.util.Iterator;
import java.util.TreeSet;

public class Universidad {
    private static Universidad universidad;
    private TreeSet<Asignatura> asignaturas;


    public static Universidad creaUniversidad(){
        if (universidad == null)
            universidad = new Universidad();
        return  universidad;
    }
    private Universidad(){
        asignaturas = new TreeSet<>();
    }

    public void agregarAsignatura(String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales){
        Asignatura asignatura = new Asignatura(cod,nombre,promocionable,cuatrimestre,tipo, clasesTotales); //CREA OBJETO asignatura
        asignaturas.add(asignatura); //AÑADE OBJETO asignatura A LA LISTA
    }

    public String detalleCatedraLibres(String cod, int C1, int C2){
        StringBuilder sb = new StringBuilder();
        sb.append("Detalle de los alumnos libres\n");
        Iterator<Asignatura> it = asignaturas.iterator();
        while(it.hasNext()) {
            Asignatura AsignaturaActual = it.next();
            int cuatrimestre = AsignaturaActual.getCuatrimestre();
            if(cuatrimestre >= C1 && cuatrimestre <= C2){
                Iterator<Inscripcion> itAsig = AsignaturaActual.getInscripciones().iterator();
                while (itAsig.hasNext()) {
                    Inscripcion inscripcionActual = itAsig.next();
                    if(inscripcionActual.Condicion().equals("Libre"))
                        sb.append(inscripcionActual.getAlumno().muestra()).append("\n");
                }
            }
        }
        return sb.toString();
    }

    public String detalleCatedra(String cod){
        Iterator<Asignatura> it = asignaturas.iterator();
        Asignatura asignaturaActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza

        while (it.hasNext() && !asignaturaActual.getCod().equals(cod))
            asignaturaActual = it.next();

        if (asignaturaActual != null && asignaturaActual.getCod().equals(cod)) {
            StringBuilder sb = new StringBuilder();
            Iterator<Inscripcion> itInsc= asignaturaActual.getInscripciones().iterator();
            sb.append("Nombre de la asignatura: ").append(asignaturaActual.getNombre()).append("\n");
            while(itInsc.hasNext()){
                Inscripcion inscripcionActual = itInsc.next();
                sb.append("Datos del alumno:\n\t").append(inscripcionActual.getAlumno().muestra());
                sb.append("\n");

                sb.append("\t");
                sb.append(inscripcionActual.muestraClases());

                sb.append("\tPorcentaje de asistencia: ").append(inscripcionActual.getAsistencias()/asignaturaActual.getClasesTotales() * 100);
                sb.append("\tModalidad: ").append(inscripcionActual.Modalidad());
                sb.append("\tCondicion Academica: ").append(inscripcionActual.Condicion());
            }
            return sb.toString();
        } else
            return "La asignatura no existe";

    }


}
