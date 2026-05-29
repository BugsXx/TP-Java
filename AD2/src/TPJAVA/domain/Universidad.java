/**
 * YA QUE VAMOS A TENER QUE USAR UNA INTERFAZ GRAFICA (Swing es lo mas facil) PARA MOSTRAR VAMOS A TENER QUE USAR
 * METODOS QUE DEVUELVAN STRINGS. CON EL FORMATO "System.out.println(...)" SOLO PODEMOS MOSTRAR EN LA CONSOLA.
 */

package TPJAVA.domain; //ESTABLECE UBICACION EN "domain"

//ACCEDE A CLASES "Asignatura" y "Inscripcion"
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.inscripciones.Inscripcion;

//ACCEDE A LIBRERIAS
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
        /// Asignatura AsignaturaActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza
        /// while (AsignaturaActual != null) {
        while(it.hasNext()) { //AÑADIDO - SINI
            Asignatura AsignaturaActual = it.next(); //MOVIDO - SINI
            int cuatrimestre = AsignaturaActual.getCuatrimestre(); //AÑADIDO - SINI
            if(cuatrimestre >= C1 && cuatrimestre <= C2){ //EDITADO - SINI

                Iterator<Inscripcion> itAsig = AsignaturaActual.getInscripciones().iterator(); //ITERADOR PARA RECORRER LISTA DE INSCRIPCIONES
                /// Inscripcion inscripcionActual = itAsig.hasNext() ? itAsig.next() : null;
                /// while (inscripcionActual != null){
                while (itAsig.hasNext()) { //EDITADO - SINI
                    Inscripcion inscripcionActual = itAsig.next(); //MOVIDO - SINI
                    if(inscripcionActual.Condicion().equals("Libre")){
                        inscripcionActual.getAlumno().muestra();
                        System.out.println("%n"); //POSIBLE CORRECION - CADA VEZ QUE MUESTRA, YA REALIZA EL SALTO DE LINEA YA QUE ES ln
                    }
                    /// inscripcionActual = itAsig.hasNext() ? itAsig.next() : null;
                }
            }
            /// AsignaturaActual = it.hasNext() ? it.next() : null; // es el ultimo? si es el ultimo, asignamos null al sig, si no, seguimos buscando
        }
    }

    public String detalleCatedra(String cod){
        Iterator<Asignatura> it = asignaturas.iterator(); //ITERADOR DE LA LISTA asignaturas
        /// Asignatura asignaturaActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza
        Asignatura asignaturaActual = null; //EDITADO - SINI
        boolean encontre=false; //AÑADIDO - SINI

        /// while (asignaturaActual != null && !asignaturaActual.getCod().equals(cod)) {
        while (it.hasNext() && !encontre) { //EDITADO - SINI
            /// asignaturaActual = it.hasNext() ? it.next() : null; // es el ultimo? si es el ultimo, asignamos null al sig, si no, seguimos buscando
            asignaturaActual = it.next(); //EDITADO - SINI
            if (asignaturaActual.getCod().equals(cod))
                encontre = true; //AÑADIDO - SINI
        }

        if (encontre) { // lo encontramos? //EDITADO(lo cambie por una flag) - SINI
            /// Iterator<Inscripcion> itAsig = asignaturaActual.getInscripciones().iterator();
            /// Inscripcion inscripcionActual = itAsig.hasNext() ? itAsig.next(): null;
            StringBuilder reporte = new StringBuilder();

            reporte.append("Nombre de la asignatura: ").append(asignaturaActual.getNombre());
            /// while(inscripcionActual != null){ // listamos todos los alumnos con sus clases asistidas
            for (Inscripcion inscripcionActual : asignaturaActual.getInscripciones()) { //AÑADIDO - SINI
                reporte.append("Datos del alumno: ");
                reporte.append(inscripcionActual.getAlumno().muestra());
                reporte.append(inscripcionActual.muestraClases());
                reporte.append("Porcentaje de asistencia: ").append(inscripcionActual.getAsistencias()/asignaturaActual.getClasesTotales() * 100);
                reporte.append("Modalidad: ").append(inscripcionActual.Modalidad());
                reporte.append("Condicion Academica: ").append(inscripcionActual.Condicion());
                /// inscripcionActual = itAsig.hasNext() ? itAsig.next() : null;
            }
            return reporte.toString();
        } else
            return "La asignatura no existe";

    }
}
