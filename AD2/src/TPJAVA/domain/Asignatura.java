package TPJAVA.domain;


import java.util.Iterator;
import java.util.LinkedList;

public class Asignatura {

    private LinkedList<Inscripcion> inscripciones; // inscriptos, pueden ser condicional, oyente o regular
    private String cod;
    private String nombre;
    private int cuatrimestre; // DE 1 A 10
    private boolean promocionable;
    private char tipo;

    private int clasesTotales;

    public int getClasesTotales(){
        return clasesTotales;
    }

    public char getTipo(){
        return tipo;
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


    public boolean estaInscripto(Alumno alumno){
        Iterator<Inscripcion> it = inscripciones.iterator();

        Inscripcion inscripcionActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza

        while (inscripcionActual != null && !inscripcionActual.getAlumno().equals(alumno)) {
            inscripcionActual = it.hasNext() ? it.next() : null; // es el ultimo? si es el ultimo, asignamos null al sig, si no, seguimos buscando
        }

        return inscripcionActual != null; // lo encontramos?

    }

    public void cargaAsistencia(Alumno alumno, MutableBoolean result) {
        Iterator<Inscripcion> it = inscripciones.iterator();

        Inscripcion inscripcionActual = it.hasNext() ? it.next() : null; // la lista esta vacia? si esta vacia asignamos null, si no la cabeza

        while (inscripcionActual != null && !inscripcionActual.getAlumno().equals(alumno)) {
            inscripcionActual = it.hasNext() ? it.next() : null; // es el ultimo? si es el ultimo, asignamos null al sig, si no, seguimos buscando
        }

        if (inscripcionActual != null) { // lo encontramos?
            inscripcionActual.marcaAsistencia();
            result.SetTrue();
        } else {
            System.out.println("El alumno no se encuentra inscripto en la asignatura.");
        }
    }

    Asignatura(String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales){
        inscripciones = new LinkedList<>();
        this.cod = cod;
        this.nombre = nombre;
        this.promocionable = promocionable;
        this.cuatrimestre = cuatrimestre;
        this.tipo = tipo;
        this.clasesTotales = clasesTotales;
    }
    // agregar metodo abstracto para inscribirse (agregar nodo en la lista dado un alumno como objeto parametro)

    // agregar mas metodos


}
