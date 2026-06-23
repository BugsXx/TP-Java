package TPJAVA.domain.asignaturas;


import TPJAVA.domain.clase.Clase;
import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.exceptions.ClaseExistenteException;
import TPJAVA.domain.asignaturas.exceptions.NoCumpleCondicionException;
import TPJAVA.domain.asignaturas.exceptions.YaInscriptoAAsignaturaException;
import TPJAVA.domain.inscripciones.InscripcionCondicional;
import TPJAVA.domain.inscripciones.InscripcionOyente;
import TPJAVA.domain.inscripciones.InscripcionRegular;
import TPJAVA.domain.inscripciones.exceptions.NoEncuentraInscripcionException;
import TPJAVA.domain.inscripciones.Inscripcion;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Asignatura implements Comparable<Asignatura>, Serializable {

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

    public void cargaAsistencia(Alumno alumno, Clase clase) throws NoEncuentraInscripcionException {

        Inscripcion inscripcionActual = buscaInscripto(alumno);

        if (inscripcionActual != null && inscripcionActual.getAlumno().equals(alumno)) { // lo encontramos?
            inscripcionActual.marcaAsistencia(clase);

        } else {
            throw new NoEncuentraInscripcionException(alumno);
        }

    }

    public Float calculaPresentismo(){
        int asistenciasTotales = 0;
        for (Inscripcion inscripcion : inscripciones) {
            asistenciasTotales += inscripcion.getAsistencias();
        }
        Float presentismo = (float) asistenciasTotales / (clasesTotales * inscripciones.size()) * 100;
        return presentismo;
    }

    public void inscribirse(Alumno alumno, char condicion)throws NoCumpleCondicionException, YaInscriptoAAsignaturaException { // referencia a un nodo de la lista de alumnos de la universidad
        Inscripcion nueva;
        if (condicion == 'r'){
            nueva = new InscripcionRegular(this, alumno);
        }
        else if (condicion == 'o'){
            nueva = new InscripcionOyente(this, alumno);
        }else if(condicion == 'c'){
            nueva = new InscripcionCondicional(this, alumno);
        }else throw new NoCumpleCondicionException("La condicion de inscripcion no existe");

        alumno.agregaInscripcion(nueva);
        inscripciones.add(nueva);
    }

    public void creaClase(Clase clase) throws ClaseExistenteException {
        if(!clases.contains(clase))
            clases.add(clase);
        else throw new ClaseExistenteException("Ya existe la clase");
    }

    public Clase getClase(String id) {
        for (Clase clase : clases) {
            if (clase.getId().equals(id)) {
                return clase;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Asignatura) {
            return ((Asignatura) obj).getCod().trim().equals(this.cod.trim());
        }
        return false;
    }

    @Override
    public int compareTo(Asignatura o) {
        int cmp = Double.compare(o.calculaPresentismo(), this.calculaPresentismo());

        if (cmp != 0) return cmp;
        return this.cod.compareTo(o.cod);
    }

    @Override
    public String toString() {
        return this.nombre + " (Cod: " + this.cod + ") - Presentismo: " + String.format("%.1f", this.calculaPresentismo()) + "%";
    }

}
