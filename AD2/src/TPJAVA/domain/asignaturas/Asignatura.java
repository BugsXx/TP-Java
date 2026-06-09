package TPJAVA.domain.asignaturas;


import TPJAVA.domain.Clase;
import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.inscripciones.exceptions.NoEncuentraInscripcionException;
import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Asignatura implements Comparable<Asignatura> {

    private List<Inscripcion> inscripciones; // inscriptos, pueden ser condicional, oyente o regular
    private List<Clase> clases; //preguntar al profe, necesario? capaz conviene hacer una clase CALENDARIO que contenga esto y este dentro
    private int clasesTotales;
    private String cod;
    private String nombre;
    private int cuatrimestre; // DE 1 A 10
    private boolean promocionable;


    public int getClasesTotales(){
        return clases.size();
    }

    public boolean getPromocionable(){
        return promocionable;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public boolean equals(Asignatura obj) {
        return obj.getCod().equals(getCod());
    }

    public String getCod(){
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Inscripcion> getInscripciones(){
        return inscripciones;
    }

    public abstract boolean cumpleCondicionPromocion(int asistenciasAlumno, float condicion);
    public abstract boolean cumpleCondicionHabilita(int asistenciasAlumno, float condicion);


    public Asignatura(String cod, String nombre, boolean promocionable, int cuatrimestre, int clasesTotales){
        clases = new LinkedList<>();
        inscripciones = new LinkedList<>();
        this.cod = cod;
        this.nombre = nombre;
        this.promocionable = promocionable;
        this.cuatrimestre = cuatrimestre;
        this.clasesTotales = clasesTotales;
    }


    private Inscripcion buscaInscripto(Alumno alumno){
        Iterator<Inscripcion> it = inscripciones.iterator();
        Inscripcion inscripcionActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza

        while (it.hasNext() && !inscripcionActual.getAlumno().equals(alumno)) {
            inscripcionActual = it.next(); // es el ultimo? si es el ultimo, asignamos null al sig, si no, seguimos buscando
        }
        return inscripcionActual;
    }

    public boolean estaInscripto(Alumno alumno){
        Inscripcion inscripcionActual = buscaInscripto(alumno);

        return inscripcionActual != null && inscripcionActual.getAlumno().equals(alumno); // lo encontramos?

    }

    public String cargaAsistencia(Alumno alumno, Clase clase) throws NoEncuentraInscripcionException {
        StringBuilder sb = new StringBuilder();

        Inscripcion inscripcionActual = buscaInscripto(alumno);

        if (inscripcionActual != null && inscripcionActual.getAlumno().equals(alumno)) { // lo encontramos?
            inscripcionActual.marcaAsistencia(clase);

        } else {
            throw new NoEncuentraInscripcionException(alumno);
        }

        return  sb.toString();
    }

    public Float calculaPresentismo(){
        int asistenciasTotales = 0;
        for (Inscripcion inscripcion : inscripciones) {
            asistenciasTotales += inscripcion.getAsistencias();
        }
        Float presentismo = (float) asistenciasTotales / (clasesTotales * inscripciones.size()) * 100;
        return presentismo;
    }

    @Override
    public int compareTo(Asignatura o) {
        return o.calculaPresentismo().compareTo(this.calculaPresentismo());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Asignatura)
            return this.calculaPresentismo().equals(((Asignatura) obj).calculaPresentismo());
        else return false;
    }
        // agregar posible metodo abstracto para inscribirse (agregar nodo en la lista dado un alumno como objeto parametro)
}
